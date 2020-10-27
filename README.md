# SimpleOnlineGame
this is just peace of software you can use on your own, you will figure out what is happening

## ClientSideProject
### Client
Basically main class that will start game and load all these things
### PacketSender
Class that I use for sending packets to server
<b>Additional use</b>
- sending PlayerJoinPacket to server
### Packetreciever
Class for reading packets i use this method:
<code>
  if(packet instanceof CoolPacket) {
    //do something
  }
</code>
### Player
Container that contains all info needed for making players
### PlayerHandler
Really crappy class that somehow works (I am handling players and rendering from there)
### InputManager
the left arrow key doesnt work
### Packet
#### PlayerJoinPacket
oh player joined lets register his uuid
#### PlayerLeavePacket
player left delete him from you list of players
#### PlayerPositionPacket (i guess this is the name of it)
hey someone moved render him on another spot
#### PlayerRequestPacket
hey the guy who moved isnt registered can you just send us his PlayerJoinPacket pls
## ServerSideProject
### Server
let me use your ServerSocket on localhost!
### ServerClient
this is peace of crap but it resends packets and do stuff like that
### Packet
#### Same as CLientSideProject
