package ru.itis.chat.controllers;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.SquareAvatar;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;

@Controller
public class PictureController {
    @Autowired
    private UserService userService;

    @GetMapping("/pic")
    public void getPicture(@RequestParam(value = "target") String target, HttpServletResponse httpServletResponse) throws IOException {
        User user = userService.getUserByLogin(target).get();
        long sum = 0;
        for(char c : user.getName().toCharArray()){
            sum+=c;
        }
        for(char c : user.getLastName().toCharArray()){
            sum+=c;
        }
        File file = new File("pic/" + user.getLogin() + ".png");
        Avatar avatar = SquareAvatar.newAvatarBuilder().size(60,60).build();
        avatar.createAsPngToFile(sum, file);

        InputStream is = new FileInputStream(file);
        httpServletResponse.setContentType("image/jpeg");
        IOUtils.copy(is, httpServletResponse.getOutputStream());
        httpServletResponse.flushBuffer();
        is.close();
    }
}
