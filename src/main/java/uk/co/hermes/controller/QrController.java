package uk.co.hermes.controller;

import net.ellise.qr.QRAction;
import net.ellise.qr.QRActionImpl;
import net.ellise.qr.QRGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class QrController {
    @Autowired
    private QRGenerator generator;

    @ResponseBody
    @GetMapping(value = "/qr/offer", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] qrOrder(@RequestParam("id") int id) {
        try {
            QRAction qrAction = new QRActionImpl();
            File file = generator.generateQRCode(qrAction, new Object[]{id});
            InputStream inputStream = new FileInputStream(file);
            return org.apache.commons.io.IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
