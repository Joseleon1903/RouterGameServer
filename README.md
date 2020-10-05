# RouterGameServer
Game server for Heroku service with Spring java webSocket.

----------------------------------  Dev Config ------------------------

## router web socket url connection 

    url: ws://localhost:8080/lobby-Manager

## create a lobby message 

    *   FactoryMessage Detail for create a lobby
    *
    *   content:
    *
    *    0             1               2          3             4             5                6
    *    Origin | operation code| lobby type| lobby code| lobby capacity| lobby Identifier| lobby status
    *
    *   SERVER&201LB&Private&QUEIO&2&jdjd:ksjd:kdjd:90&Online

## connect to lobby message 

    *   ConnectToLobbyMessage connect client to Lobby
    *
    *
    *    0             1               2          3             4             5           6
    *    Origin | operation code| lobby type| lobby code|  playeraName| player Session| isHost
    *
    *   CLIENT&202LB&Private&QUEIO&mario01&jdhfsdfkdjjdssd88&0
 
 ## send message to lobby client
 
    *   SimpleContentMessage common message from a player lobby
    *
    *   content:
    *
    *    0             1               2         3           4        continue
    *    Origin | operation code| lobby code|  data   |   .....
    *
    *   CLIENT&102LB&QUEIO&data