package com.mapia.game.logic;

import com.mapia.domain.Player;
import com.mapia.domain.Room;
import com.mapia.domain.User;
import com.mapia.domain.role.Role;
import com.mapia.game.GamePlayers;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jbee on 2017. 5. 17..
 */
public class LogicTest {

    User user1;
    User user2;
    User user3;
    User user4;
    User user5;
    User user6;
    User user7;
    User user8;
    Set<User> users = new LinkedHashSet<>();

    @Before
    public void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setNickname("a");

        user2 = new User();
        user2.setId(2);
        user2.setNickname("b");

        user3 = new User();
        user3.setId(3);
        user3.setNickname("c");

        user4 = new User();
        user4.setId(4);
        user4.setNickname("d");

        user5 = new User();
        user5.setId(5);
        user5.setNickname("e");

        user6 = new User();
        user6.setId(6);
        user6.setNickname("f");

        user7 = new User();
        user7.setId(7);
        user7.setNickname("g");

        user8 = new User();
        user8.setId(8);
        user8.setNickname("h");
    }

    @Test
    public void 네명일때_승패분석() {
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        GamePlayers players = new GamePlayers(users);

        Map<Role, Player> voteStatus = new HashMap<>(users.size());
        players.judgeGameResult(voteStatus);

    }
}
