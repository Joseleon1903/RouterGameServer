# RouterGameServer
Game server for Heroku service with Spring java webSocket.

----------------------------------  Dev Config ------------------------

## router web socket url connection 

    url: ws://localhost:8080/checker/lobby-manager
    
    url: ws://localhost:8080/chess/lobby-manager


## create a lobby message 

    *   FactoryMessage Detail for create a lobby
    *
    *   content:
    *
    *    0             1               2          3        4             5                6            7           8             9
    *    Origin | operation code| lobby type| lobby code|lobby Map| lobby time|  lobby capacity| gametype lobby Identifier| lobby status
    *
    *    CLIENT&202LB&Public&GYTQE&PL2&GuestPlayer051072&8846074&Dominican Republic&UN:IDENT:FIER&0

## connect to lobby message 

     *   ConnectToLobbyMessage connect client to Lobby
     *
     *
     *    0             1               2          3             4           5           6                  7            8            9
     *    Origin | operation code| lobby type| lobby code| playerCode | playeraName| playertId | playernationality |player Session| isHost
     *
     *    CLIENT&202LB&Public&GYTQE&PL2&GuestPlayer051072&8846074&Dominican Republic&UN:IDENT:FIER&0
 
 ## send message to lobby client
 
    *   SimpleContentMessage common message from a player lobby
    *
    *   content:
    *
    *    0             1               2         3           4        continue
    *    Origin | operation code| lobby code|  data   |   .....
    *
    *   CLIENT&102LB&QUEIO&data
    
 ## response message to client from server lobby
 
    *   lost connection of player 
    *
    *   content:
    *
    *    0        1        2       
    *    Origin| operation code| message
    *
    *   SERVER|102LB|CONNECTIONLOST
    
    
 ## response message to client from server lobby
 
    *   Lobby is Full of player
    *
    *   content:
    *
    *   SERVER|202LB|LOBBYREADY|PL1|PlayerName|PlayerId|Playernationality|PL2|PlayerName|PlayerId|PlayerNationality
      
 ## response message to client from server lobby

    /**\
     *   StartGameMessage common message start lobby game
     *
     *   content:
     *
     *    0                  1            2          3           4          5          6
     *    destination | operation code| result | lobby code | game map | game time | gameType
     *
     *   SERVER|202LB|START|CDMPR|Park|Day|CHECKER
     */