//package net.fanzhiwei.web;
//
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author fanzhiwei
// */
//@Controller
//public class MyErrorController implements ErrorController {
//
//    private static final String ERROR_PATH = "/error";
//
//
//    @RequestMapping(produces = "text/html")
//    public ModelAndView errorHtml(HttpServletRequest request,
//                                  HttpServletResponse response) {
//        HttpStatus status = getStatus(request);
//        return new ModelAndView();
//    }
//
//    @RequestMapping
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = new HashMap<>();
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }
//
//    @Override
//    public String getErrorPath() {
//        return ERROR_PATH;
//    }
//
//    protected HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request
//                .getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        try {
//            return HttpStatus.valueOf(statusCode);
//        }
//        catch (Exception ex) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//    }
//}
