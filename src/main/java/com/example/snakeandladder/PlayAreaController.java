package com.example.snakeandladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Random;

public class PlayAreaController
{
    @FXML
    ImageView player1,player2;



    int turn=1;
    @FXML
    Text yourNumber;

    @FXML
    Text changeTurn;

    HashMap<Pair<Double,Double>,Pair<Double,Double>>snakeLadderPosition=new HashMap<>();
    @FXML
    public void rollDice(MouseEvent event)
    {
        getSnakeLadderCoordinates();
        Random random=new Random();
        int roll= random.nextInt(6)+1;

        if(turn == 1)
        {
            if(roll==6)
            {
                yourNumber.setText("Player 1 got "+roll);
            }
            else
            {
                turn = 2;
                yourNumber.setText("Player 1 got "+roll);
                changeTurn.setText("Player 2's Turn");
            }
            Pair<Double,Double> moveCoordinates=movement(player1.getTranslateX(),player1.getTranslateY(),roll);
            player1.setTranslateX(moveCoordinates.getKey());
            player1.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderPosition.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player1.setTranslateX(snakeLadderPosition.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeLadderPosition.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());
        }
        else
        {
            if(roll==6)
            {
                yourNumber.setText("Player 2 got "+roll);
            }
            else
            {
                turn=1;
                yourNumber.setText("Player 2 got "+roll);
                changeTurn.setText("Player 1's Turn");
            }
            Pair<Double,Double> moveCoordinates=movement(player2.getTranslateX(),player2.getTranslateY(), roll);
            player2.setTranslateX(moveCoordinates.getKey());
            player2.setTranslateY(moveCoordinates.getValue());
            if(snakeLadderPosition.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player2.setTranslateX(snakeLadderPosition.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeLadderPosition.get(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }

    }

    Pair<Double,Double>movement(double X, double Y, int roll)
    {
        double moveX=X;
        double moveY=Y;

        if((moveY/63)%2==-1)
        {
            moveX-=roll*63;
            if(moveX<=0)
            {
                double temp=-moveX;
                moveX=63;
                moveY-=63;
                moveX+=temp;
            }
        }
        else {
            moveX += roll * 63;
            if(moveX>630)
            {
                moveX=630*2-moveX+63;
                moveY-=63;
            }
        }

        if(moveY==-630)
        {
            moveY=-567;
            moveX=X;
        }

        return new Pair<>(moveX,moveY);
    }

    void checkWin(Double X, Double Y){
        if(X==63 && Y==-567)
        {
            Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
            winAlert.setHeaderText("Result");
            if(turn==1)
            {
                winAlert.setContentText("Player 1 has Won");
            }
            else
            {
                winAlert.setContentText("Player 2 has Won");
            }
            winAlert.showAndWait();
        }
    }
    void getSnakeLadderCoordinates()
    {
        snakeLadderPosition.put(new Pair<>(126.0,0.0),new Pair<>(189.0,-126.0)); // L - 2
        snakeLadderPosition.put(new Pair<>(378.0,0.0),new Pair<>(315.0,-252.0)); // L - 6
        snakeLadderPosition.put(new Pair<>(063.0,-063.0),new Pair<>(126.0,-315.0)); //L - 20
        snakeLadderPosition.put(new Pair<>(252.0,-315.0),new Pair<>(315.0,-567.0)); //L - 57
        snakeLadderPosition.put(new Pair<>(567.0,-315.0),new Pair<>(567.0,-441.0)); //L - 52
        snakeLadderPosition.put(new Pair<>(630.0,-441.0),new Pair<>(567.0,-567.0)); //L - 71

        snakeLadderPosition.put(new Pair<>(189.0,-567.0),new Pair<>(063.0,-189.0));
        snakeLadderPosition.put(new Pair<>(189.0,-252.0),new Pair<>(252.0,-063.0));
        snakeLadderPosition.put(new Pair<>(252.0,-504.0),new Pair<>(189.0,-315.0));
        snakeLadderPosition.put(new Pair<>(315.0,-315.0),new Pair<>(504.0,0.0));
        snakeLadderPosition.put(new Pair<>(441.0,-504.0),new Pair<>(567.0,-252.0));
        snakeLadderPosition.put(new Pair<>(504.0,-441.0),new Pair<>(378.0,-063.0));
        snakeLadderPosition.put(new Pair<>(630.0,-252.0),new Pair<>(315.0,0.0));

    }
}
