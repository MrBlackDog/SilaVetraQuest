package com.example.navigationarproject;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by Игорь on 21.10.2018.
 */

public class WebSocketGolos extends WebSocketListener {

    /*private Color parseColor(String Message){
        int Red = Integer.parseInt(Message.split(",")[0]);
        int Green = Integer.parseInt(Message.split(",")[1]);
        int Blue = Integer.parseInt(Message.split(",")[2]);
        return new Color(Red/255f,Green/255f,Blue/255f,1);
    }*/

    WebSocketGolos()
    {
    }
    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }
    @Override
    public void onMessage(WebSocket webSocket, String text)
    {
        String[] Mass = text.split(":");
        String Code = Mass[0];

        String[] Message = {};
        if (Mass.length > 1) {
            Message = Mass[1].split(" ");
        }
       //  loggerFragment.SetTextMessage("xd");
       // String Guid;
       // Color color;
       /* switch (Code){
            case "id":
                Game.Me.Connected = true;
                Guid = Message[0];
                color = parseColor(Message[1]);
                int MyIndex= Integer.parseInt(Message[2]);
                Game.Me.SetGUIDAndColor(Guid,color,MyIndex);
                break;
            case "NewPlayer":
                Guid = Message[0];
                color = parseColor(Message[1]);
                String Nick = Message[2];
                int NewPlayerIndex = Integer.parseInt(Message[3]);
                Game.AddPlayer(Guid, color, Nick,NewPlayerIndex);
                Game.Me.Connected = true;
                break;
            case "Throw":
                Guid = Message[0];

                //int Color = Integer.parseInt(Message[1]);

                //Касательная
                float x = Float.parseFloat(Message[2]);
                float y = Float.parseFloat(Message[3]);
                float z = Float.parseFloat(Message[4]);

                float tanx = Float.parseFloat(Message[5]);
                float tany = Float.parseFloat(Message[6]);
                float tanz = Float.parseFloat(Message[7]);

                long mills = Game.time;
                Player player = Game.FindPlayerByGuid(Guid);
                if (player != null)
                    player.ThrowBall(x, y, z, tanx, tany, tanz, mills);
                Game.Me.Connected = true;
                break;
            case "Close":
                Guid = Message[0];
                Game.FindPlayerByGuid(Guid).Close();
                Game.Me.CloseWithoutMessage();
                Game.Stop();
                break;
            case "ConnectionLost":
                Guid = Message[0];
                Game.FindPlayerByGuid(Guid).ConnectionLost();
                Game.Stop();
                break;
            case "ConnectionFind":
                Guid = Message[0];
                Game.FindPlayerByGuid(Guid).ReConnect();
                break;
            case "Start":
                Color Needcolor = parseColor(Message[0]);
                Game.StartGame(Needcolor,parseColor(Message[1]));
                break;
            case "Check":
                Game.Me.Answer();
                synchronized (Game.Me.locker) {
                    Game.Me.Connected = true;
                }
                break;
            case "Stop":
                Game.Stop();
                break;
            case "NewLickerColor":
                Game.licker.SetNewNeedColor(parseColor(Message[0]));
                Game.Score++;
                break;
            case "NewPlayerColor":
                Guid = Message[0];
                Game.FindPlayerByGuid(Guid).SetColor(parseColor(Message[1]));
                break;
            case "NewCurrentColor":
                Color cl = parseColor(Message[0]);
                Game.licker.SetCurrentColor(cl);
                break;
            case "Error":
                Game.Me.Connected = false;
                String ErrorMessage = Message[0];
                Game.NonConnectReason = ErrorMessage;
                Game.Me.CloseWithoutMessage();
                break;
            case "Ready":
                Guid = Message[0];
                boolean ready = Boolean.getBoolean(Message[1]);
                Game.ChangePlayerStatus(Guid,ready);
                break;
            case "TimeLeft":
                Game.Time = Integer.parseInt(Message[0]);
                break;
            case "TimeToGo":
                Game.TimeToGo = Integer.parseInt(Message[0]);
                break;
            case "Go":
                Game.Start = true;
                break;
        }*/
    }
}
