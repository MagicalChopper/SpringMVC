package com.coder.controller;

import com.coder.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 简单跳转
     * @return
     */
    @RequestMapping("/test")
    public String testUser(){
        return "user";
    }


    /**
     * 带数据跳转页面ModelAndView法
     * @return
     */
    @RequestMapping("mdv")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        User user = new User();
        user.setName("lh");
        user.setId(22);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    /**
     * 带数据跳转页面ModelMap法
     * @param modelMap
     * @return
     */
    @RequestMapping("mm")
    public String testModelMap(ModelMap modelMap){
        User user = new User();
        user.setName("lh");
        user.setId(23);
        modelMap.addAttribute("user",user);
        return "user";
    }



    /**
     * 重定向
     */
    @RequestMapping("redirect")
    public String testRedirect(ModelMap modelMap){
        User user = new User();
        user.setName("lh");
        user.setId(23);
        modelMap.addAttribute("user",user);
        return "redirect:/user/demo";//请求转发到UserController的/user/demo路径也就是下面这个方法
    }

    @RequestMapping("demo")
    public String demo(){
        return "demo";
    }


                                /**
                                 *
                                 *上面是后台向前台传数据
                                 * ----------------------------
                                 * 下面是前台向后台传数据
                                 *
                                 * /


    /**
     * get 请求 网页地址栏输入localhost:8080/user/get/1/lh测试
     */
    @RequestMapping("get/{id}/{name}")
    public String testGet( @PathVariable("id") Integer id, @PathVariable("name") String name){
        System.out.println(id);
        System.out.println(name);
        return "user";
    }

    /**
     * post请求 使用HttpServletRequest
     * @return
     */
    @RequestMapping("post")
    public String testPost(HttpServletRequest req){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        System.out.println(id);
        System.out.println(name);
        return "user";
    }

    /**
     * post 参数放对象
     * @param user
     * @return
     */
    @RequestMapping("post/obj")
    public String testPostObject(User user ){
        System.out.println(user.getId());
        System.out.println(user.getName());
        return "user";
    }

    /**
     * 输出到前端一个json
     */
    @RequestMapping("json")
    @ResponseBody
    public User testJson(){
        User user = new User();
        user.setName("lh");
        user.setId(23);
        return user;
    }

    /**
     * 前端传过来一个json，将json传入对象中
     */
    @RequestMapping("/postjson")
    public String testPostJson(@RequestBody User user){//@RequestBody注解代表请求参数是Json
        return "demo";
    }

    /**
     * 测试拦截器
     * @return
     */
    @RequestMapping("/interceptor")
    public String testInterceptor(){
        return "demo";
    }

    /**
     * 测试文件上传
     */
    @RequestMapping("toUpload")
    public String toUpload(){
        return "upload";
    }

    /**
     * 单文件上传
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
//    @RequestMapping("upload")
//    public String testUpload(MultipartFile file,HttpServletRequest request) throws IOException {
//        String name = file.getOriginalFilename();
//        File uploadFile = new File("/"+name);//项目所在磁盘根目录
//        file.transferTo(uploadFile);
//        return "demo";
//    }

    /**
     * 多文件上传
     * @return
     * @throws IOException
     */
//    @RequestMapping("upload")
//    public String testUpload(MultipartFile[] file,HttpServletRequest request) throws IOException {
//        for (MultipartFile multipartFile : file) {
//            String name = multipartFile.getOriginalFilename();
//            File uploadFile = new File("/"+name);//项目所在磁盘根目录
//            multipartFile.transferTo(uploadFile);
//        }
//        return "demo";
//    }

    /**
     * 文件下载，不能显示？
     * @return
     * @throws IOException
     */
    @RequestMapping("download")
    public ResponseEntity<byte[]> download() throws IOException {
        String path = "F:\\a1.jpg";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",path);
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }


}
