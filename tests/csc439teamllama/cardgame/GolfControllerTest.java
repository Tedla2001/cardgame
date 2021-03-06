package csc439teamllama.cardgame;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GolfControllerTest {

    @Test
    void gameStart() {
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        Collections.addAll(((GolfCLITestView)controllerTest.view).input, "10","-1","2","-1");
        controllerTest.GameStart();
        assertThat(controllerTest.game.deck.size()).isEqualTo(44);
        assertThat(controllerTest.game.players[0].hand[0].getFacing()).isEqualTo(playingCard.Facing.UP);
        assertThat(controllerTest.game.players[3].hand[1].getFacing()).isEqualTo(playingCard.Facing.UP);
        assertThat(controllerTest.game.players[5].hand[5].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(controllerTest.game.players[0].hand[0].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controllerTest.game.players[0].hand[0].getNumber()).isEqualTo(playingCard.Number.KING);
        assertThat(controllerTest.game.players[6].hand[4].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(controllerTest.game.players[6].hand[4].getNumber()).isEqualTo(playingCard.Number.QUEEN);
        assertThat(controllerTest.game.players[9].hand[3].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controllerTest.game.players[9].hand[3].getNumber()).isEqualTo(playingCard.Number.EIGHT);
        controllerTest.GameStart();
        assertThat(controllerTest.game.deck.size()).isEqualTo(40);
        assertThat(((GolfCLITestView) controllerTest.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 44 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 40 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void ControllerPromptReDisplay(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"4","-1","1");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        controller.game.turn = 1;
        controller.game.gameOver = false;
        controller.game.players[0].hand = new playingCard[]{new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE),
                                                            new playingCard(playingCard.Facing.UP, playingCard.Suit.DIAMONDS, playingCard.Number.KING),
                                                            new playingCard(playingCard.Facing.UP, playingCard.Suit.CLUBS, playingCard.Number.TWO),
                                                            new playingCard(true,4,12),
                                                            new playingCard(true,4,3),
                                                            new playingCard(true,3,11)};
        ((GolfCLITestView)controller.view).output = "";
        controller.Turn();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "Player 1's Turn\n"+
                        "1.spades,ace 2.diamonds,king 3.clubs,two\n"+
                        "4.hearts,queen 5.hearts,three 6.clubs,jack\n"+
                        "The deck has 28 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "

                        +"Player 1's Turn\n"+
                        "1.spades,ace 2.diamonds,king 3.clubs,two\n"+
                        "4.hearts,queen 5.hearts,three 6.clubs,jack\n"+
                        "The deck has 28 remaining\n"+
                        "The discard pile is empty\n"
        );
    }

    @Test
    void PlayerExitsDuringGameOptions(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"-1");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "
        );
        assertThat(controller.game).isNull();
    }

    @Test
    void PlayerExitsDuringDecision(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"5","-1");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 74 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }
    @Test
    void PlayerExitsDuringGameDiscard(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"5","2","-1");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 74 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "Drawn card: diamonds,nine From: Deck\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n"+
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: ");
    }
    @Test
    void TryToDrawDiscardEmpty(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"5","3","-1");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 74 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "Discard Is Empty! Please Draw from Deck\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void TryToDrawDeckEmpty(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"5","-1","2");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        controller.game.turn = 1;
        ((GolfCLITestView)controller.view).output = "";
        controller.game.discard = controller.game.deck;
        controller.game.deck.clear();
        controller.Turn();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 0 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "Deck Is Empty! Please Draw from Discard\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }
    @Test
    void OutOfBoundsGameOptions(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"0","-2","sddsddsd");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+
                        "please input a number: greater than 0\n"+
                        "How many players for this game: "+
                        "please input a number: greater than 0\n"+
                        "How many players for this game: "+
                        "please input a number: greater than 0\n"+
                        "How many players for this game: "
        );
        assertThat(controller.game).isNull();
    }

    @Test
    void OutOfBoundsDecision(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"7","0","44","abassjhd");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+
                        "Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 62 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "please input a number between 1 and 3\n"+"1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+

                        "please input a number between 1 and 3\n"+"1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+

                        "please input a number between 1 and 3\n"+"1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }
    @Test
    void OutOfBoundsDiscard(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"6","-1","2","0","44","abassjhd");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        controller.game.turn = 1;
        ((GolfCLITestView)controller.view).output = "";
        controller.Turn();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 68 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "Drawn card: diamonds,three From: Deck\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n"+
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+
                        "please input a number between 1 and 7\n"+
                        "Drawn card: diamonds,three From: Deck\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n"+
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+
                        "please input a number between 1 and 7\n"+
                        "Drawn card: diamonds,three From: Deck\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n"+
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+
                        "please input a number between 1 and 7\n"+
                        "Drawn card: diamonds,three From: Deck\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n"+
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "
                );
    }
    @Test
    void discardToDiscardFromSelf(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"4","-1","3","7");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        ((GolfCLITestView)controller.view).output = "";
        controller.game.discard.add(new playingCard(true,1,1));
        controller.game.turn = 1;
        controller.game.gameOver = false;
        controller.Turn();
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 28 remaining\n"+
                        "The top card on the discard pile is spades,ace\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+
                        "Drawn card: spades,ace From: Discard\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                            "Enter Number To Proceed: "+
                        "You Picked This Card From Discard! Please Discard A Card From Your Hand.\n"
                        +
                        "Drawn card: spades,ace From: Discard\n"+
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "
        );
    }
    @Test
    void drawDeckDiscardFromDeck(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"5","2","7");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("diamonds,nine");
    }
    @Test
    void DrawDeckDiscardFromHand(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"3","2","3");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("hearts,jack");
        assertThat(controller.game.players[0].hand[2].toString()).isEqualTo("clubs,eight");
        controller.game.players[2].hand[5].flipCard();
        assertThat(controller.game.players[2].hand[5].toString()).isEqualTo("clubs,nine");
    }

    @Test
    void DrawDiscardDiscardFromHand(){
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll(view.input,"6","2","6","3","6");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        controller.game.turn = 2;
        controller.view.DisplayHand(controller.game);
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("hearts,two");
        assertThat(controller.game.players[0].hand[5].toString()).isEqualTo("diamonds,three");
        assertThat(controller.game.players[1].hand[5].toString()).isEqualTo("hearts,eight");
    }
}