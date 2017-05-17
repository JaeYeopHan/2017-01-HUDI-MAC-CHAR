package com.mapia.game;

import com.mapia.domain.Player;
import com.mapia.domain.User;
import com.mapia.domain.role.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mapia.domain.Player;
import com.mapia.domain.User;
import com.mapia.websocket.VoteMessage;

public class GameManager {

    private GamePlayers players;

    public GameManager(Set<User> users) {
        this.players = new GamePlayers(users);
        RoleManager.assignRoleToPlayers(this.players);
    }

    public String findRoleNameByUserNickName(String userNickName) {
        return this.players.findRoleName(userNickName);
    }

    public List<Player> getPlayers() {
        return this.players.getPlayers();
    }

    // 게임이 끝나면 "finished"
    // 게임이 아직 안 끝났으면 이번 투표 결과로 죽은 사람 return
    public String judgeGameResult(Map<Role, Player> voteStatus) {
        return players.judgeGameResult(voteStatus);
    }
}
