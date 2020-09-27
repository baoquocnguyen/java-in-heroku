package com.sakadream.test.controller;


import com.sakadream.test.bean.VideoYouTube;
import com.sakadream.test.model.Functions;
import com.sakadream.test.model.Video;
import com.sakadream.test.model.UserSpace;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class YouTubeController {
    Functions fn = new Functions();

    Video videoFunction = new Video();
    UserSpace userspace = new UserSpace();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("usernameId") String usernameId, @RequestParam("password") String password,
            HttpSession session, ModelMap model, HttpServletResponse response, HttpServletRequest request) throws Exception {
        if (fn.checkLogin(usernameId, password, session)) {
            model.addAttribute("user", userspace.getUser(usernameId));


                System.out.println("Redirect to ShowMeServlet");

                // contextPath: Là một String rỗng "" hoặc khác rỗng.
                // Nếu khác rỗng, nó luôn bắt đầu bởi /
                // và không kết thúc bởi /
                String contextPath = request.getContextPath();

                // ==> /ServletTutorial/showMe
                response.sendRedirect(contextPath + "/home.htm");

            //response.sendRedirect( "http://localhost:8080/userspace.htm");
            return "home";
        } else {
            model.addAttribute("error", 1);
            return "index";
        }
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String userspace(HttpSession session, ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            model.addAttribute("user", userspace.getUser(session.getAttribute("usernameId").toString()));
            return "home";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/videoyoutube", method = RequestMethod.GET)
    public String youtube(HttpSession session, ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            model.addAttribute("listVideoYouTube", videoFunction.myAllVideoYouTube(session.getAttribute("usernameId").toString()));
            return "pagesYoutube/videoyoutube";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/videoyoutube-view", method = RequestMethod.GET)
    public String youtubeview(HttpSession session, ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            model.addAttribute("listVideoYouTube", videoFunction.viewOthersVideoYouTube(session.getAttribute("usernameId").toString()));
            return "pagesYoutube/videoyoutube-view";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/add-videoyoutube", method = RequestMethod.POST)
    public String addvideoyoutube(@RequestParam("VideoID") String VideoID, @RequestParam("CoinValue") int CoinValue,
                                  @RequestParam("DailyClick") int DailyClick, @RequestParam("TotalClick") int TotalClick,
                                  @RequestParam("Active") boolean Active, HttpSession session, HttpServletResponse response,HttpServletRequest request, ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            videoFunction.addVideoYouTube(new VideoYouTube(VideoID, session.getAttribute("usernameId").toString(), CoinValue, DailyClick, TotalClick,Active));
            model.addAttribute("listVideoYouTube", videoFunction.showAllVideoYouTube());



            System.out.println("Redirect to videoyoutube page");

            // contextPath: Là một String rỗng "" hoặc khác rỗng.
            // Nếu khác rỗng, nó luôn bắt đầu bởi /
            // và không kết thúc bởi /
            String contextPath = request.getContextPath();

            // ==> /ServletTutorial/showMe
            response.sendRedirect(contextPath + "/videoyoutube.htm");

            return "pagesYoutube/videoyoutube";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/addvideoyoutube", method = RequestMethod.GET)
    public String add(HttpSession session) {
        if (fn.checkSession(session)) {
            return "pagesYoutube/addvideoyoutube";
        } else {
            return "index";
        }
    }


    @RequestMapping(value = "/delete-videoyoutube", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id, HttpSession session, ModelMap model) throws Exception {
        if (fn.checkSession(session)) {
            videoFunction.deleteVideoYouTube(Integer.valueOf(id));
            model.addAttribute("listVideoYouTube", videoFunction.showAllVideoYouTube());
            return "videoyoutube";
        } else {
            return "index";
        }
    }





   }