package com.example.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.player.repository.PlayerRepository;
import com.example.player.model.*;

@Service
public class PlayerH2Service implements PlayerRepository{
    @Autowired
    private JdbcTemplate db;

    public ArrayList<Player> getAllPlayer(){
        List<Player> arrLi = db.query("select * from team",new PlayerRowMapper());
        ArrayList<Player> players = new ArrayList<>(arrLi);
        return players;
    }
    public Player getPlayer(int playerId){
        try{
                Player player = db.queryForObject("select * from team where playerId=?",new PlayerRowMapper(), playerId);
        return player;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }
    public Player addPlayer(Player player){
        db.update("insert into team(playerName,jerseyNumber,role) values(?,?,?)",player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        Player updatedData = db.queryForObject("select * from team where playerName=? and jerseyNumber=?",new PlayerRowMapper(), player.getPlayerName(),player.getJerseyNumber());
        return updatedData;
    }
    public Player updatePlayer(int playerId,Player player){
        if(player.getPlayerName() != null){
            db.update("update team set playerName=? where playerId=?",player.getPlayerName(),playerId);
        }
        if(player.getJerseyNumber() != 0){
            db.update("update team set jerseyNumber=? where playerId=?",player.getJerseyNumber(),playerId);
        }
        if(player.getRole() != null){
            db.update("update team set role=? where playerId=?",player.getRole(),playerId);
        }
        return getPlayer(playerId);
    }
    public void deletePlayer(int playerId){
        try{
        db.update("delete from team where playerId=?",playerId);
        throw new ResponseStatusException(HttpStatus.OK);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.OK);
        }
        
         
    }
}