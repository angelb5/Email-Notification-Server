package com.example.xd.Service;

import com.example.xd.DTO.ReservaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

@EnableAsync
@Service
@RequiredArgsConstructor
public class EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private static final String FROM = "GoLend <notifications@golend.site>";
    DateFormat df = new SimpleDateFormat("EEE dd MMM yyy", new Locale("es"));

    public void correoSolicitud(ReservaDTO reserva, String correo) throws MessagingException {
        if (reserva.getEstado().equals("Pendiente de aprobación")) return;
        Context context = new Context();
        String subject = "Lo sentimos, tu solicitud ha sido rechazada";
        if (reserva.getEstado().equals("Solicitud aceptada")) {
            String mapUrl = "https://api.mapbox.com/styles/v1/mapbox/streets-v12/static/pin-l+f74e4e("+
                    reserva.getLugarRecojo().getLongitude()+","+reserva.getLugarRecojo().getLatitude()+
                    ")/"+reserva.getLugarRecojo().getLongitude()+","+reserva.getLugarRecojo().getLatitude()+
                    ",16,170,20/750x450?access_token=pk.eyJ1IjoiYW5nZWxiNSIsImEiOiJjbDlyZXdjMmUwaHZkM25xbWRyMXkxYXNoIn0.XFRW7QdnkPbGTSZ0nOBtSw";
            context.setVariable("mapUrl", mapUrl);
            subject = "Tu solicitud ha sido aprobada";
        }
        String fechaResp = df.format(reserva.getHoraRespuesta().toDate());
        context.setVariable("fechaResp", fechaResp);
        context.setVariable("reserva", reserva);
        String process = templateEngine.process("solicitud_mail", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setSubject(subject);
        helper.setText(process, true);
        helper.setFrom(FROM);
        helper.setTo(correo);
        javaMailSender.send(mimeMessage);
    }
}
