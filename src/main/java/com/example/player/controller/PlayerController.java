package com.example.player.controller;
 import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.player.service.PlayerH2Service;
import com.example.player.model.Player;



@RestController
 public class PlayerController{

   @Autowired
   public PlayerH2Service playerService;

   @GetMapping("/players")
   public ArrayList<Player> getAllPlayer(){
      return playerService.getAllPlayer();
  }

  @GetMapping("/players/{playerId}")
  public Player getPlayer(@PathVariable int playerId){
    return playerService.getPlayer(playerId);
  }

  @PostMapping("/players")
  public Player addPlayer(@RequestBody Player player){
    return playerService.addPlayer(player);
  }

  @PutMapping("/players/{playerId}")
  public Player updatePlayer(@PathVariable int playerId, @RequestBody Player player){
    return playerService.updatePlayer(playerId,player);
  }

@DeleteMapping("/players/{playerId}")
  public void deletePlayer(@PathVariable int playerId){
    playerService.deletePlayer(playerId);
  }



  

 }
