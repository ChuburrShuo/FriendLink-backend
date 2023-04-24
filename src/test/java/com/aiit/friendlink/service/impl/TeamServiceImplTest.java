package com.aiit.friendlink.service.impl;

import com.google.gson.Gson;
import com.aiit.friendlink.model.entity.Team;
import com.aiit.friendlink.model.entity.User;
import com.aiit.friendlink.service.TeamService;
import com.aiit.friendlink.service.UserService;
import com.aiit.friendlink.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class TeamServiceImplTest {

    @Resource
    private UserService userService;
    @Resource
    private TeamService teamService;

    @Test
    void dissolutionExpiredTeam() {
        List<Team> teamList = teamService.list();
        Set<Long> teams = teamList.stream()
                .filter(team -> new Date().after(team.getExpireTime()))
                .map(Team::getId).collect(Collectors.toSet());

        List<User> userList = userService.list();
        userList.forEach(user -> {
            Set<Long> userTeamIds = StringUtils.stringJsonListToLongSet(user.getTeamIds());
            System.err.println("前" + userTeamIds);
            for (Long teamId : teams) {
                userTeamIds.remove(teamId);
            }
            user.setTeamIds(new Gson().toJson(userTeamIds));
            userService.updateById(user);
            System.err.println("后" + userTeamIds);
        });
        boolean dissolutionExpiredTeam = teamService.removeByIds(teams);
        // Assertions.assertTrue(dissolutionExpiredTeam);
    }
}