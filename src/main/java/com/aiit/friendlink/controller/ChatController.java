package com.aiit.friendlink.controller;

import com.aiit.friendlink.exception.BusinessException;
import com.aiit.friendlink.common.BaseResponse;
import com.aiit.friendlink.common.ErrorCode;
import com.aiit.friendlink.common.ResultUtil;
import com.aiit.friendlink.model.entity.User;
import com.aiit.friendlink.model.request.ChatRequest;
import com.aiit.friendlink.model.vo.MessageVo;
import com.aiit.friendlink.service.ChatService;
import com.aiit.friendlink.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.aiit.friendlink.contant.ChatConstant.*;

/**
 * @author Shuoliu
 * @Date: 2023年04月11日 11:37
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private ChatService chatService;
    @Resource
    private UserService userService;

    @PostMapping("/privateChat")
    public BaseResponse<List<MessageVo>> getPrivateChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> privateChat = chatService.getPrivateChat(chatRequest, PRIVATE_CHAT, loginUser);
        return ResultUtil.success(privateChat);
    }

    @GetMapping("/hallChat")
    public BaseResponse<List<MessageVo>> getHallChat(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> hallChat = chatService.getHallChat(HALL_CHAT, loginUser);
        return ResultUtil.success(hallChat);
    }

    @PostMapping("/teamChat")
    public BaseResponse<List<MessageVo>> getTeamChat(@RequestBody ChatRequest chatRequest, HttpServletRequest request) {
        if (chatRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求有误");
        }
        User loginUser = userService.getLoginUser(request);
        List<MessageVo> teamChat = chatService.getTeamChat(chatRequest, TEAM_CHAT, loginUser);
        return ResultUtil.success(teamChat);
    }
}
