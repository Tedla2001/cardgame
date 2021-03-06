package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static com.google.common.truth.Truth.*;

class GolfViewTest {


    @Test
    void TitleScreenTest(){
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        controllerTest.view.TitleScreen();
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
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n");
    }

    @Test
    void gameStartOptions() {
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        Collections.addAll(((GolfCLITestView)controllerTest.view).input, "10","-1");
        assertThat(controllerTest.view.GameStartOptions()).isEqualTo(10);
        assertThat(((GolfCLITestView) controllerTest.view).output).isEqualTo(
        "How many players for this game: "
        );
    }

    @Test
    void displayGameStateWithEmptyDiscard() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.GameStart();
        assertThat((view).output).isEqualTo(


                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"
                        +"How many players for this game: "
                        +"Player 1's Turn\n"+
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
    void displayGameStateWithoutEmptyDiscard() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.GameStart();
        controllerTest.game.discard.add(controllerTest.game.deck.remove(controllerTest.game.deck.size()-1));
        controllerTest.game.discard.get(controllerTest.game.discard.size()-1).flipCard();
        controllerTest.game.turn=1;
        view.DisplayGameState(controllerTest.game);
        assertThat((view).output).isEqualTo(

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
                        "Enter Number To Proceed: Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 39 remaining\n"+
                        "The top card on the discard pile is hearts,ace\n"

        );

    }

    @Test
    void promptDecision() throws InputMismatchException {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2","3","-1","0","a");
        assertThat(view.PromptDecision()).isEqualTo(2);
        assertThat(view.PromptDecision()).isEqualTo(3);
        assertThat(view.PromptDecision()).isEqualTo(-1);
        InputMismatchException error = assertThrows(InputMismatchException.class,()->{
           view.PromptDecision();
        });
        error = assertThrows(InputMismatchException.class,()->{
            view.PromptDecision();
        });
        assertThat(view.output).isEqualTo(
                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
        "3. Pick Up From Discard\n"+
        "Or Enter -1 To Exit\n"+
        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void promptDiscard() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "4","-1","2","3","-1","0","a");
        GolfController controller = new GolfController(view);
        controller.GameStart();
        ((GolfCLITestView)controller.view).output = "";
        controller.game.turn=1;
        assertThat(view.PromptDiscard(controller.game)).isEqualTo(2);
        assertThat(view.PromptDiscard(controller.game)).isEqualTo(3);
        assertThat(view.PromptDiscard(controller.game)).isEqualTo(-1);
        InputMismatchException error = assertThrows(InputMismatchException.class,()->{
            view.PromptDiscard(controller.game);
        });
        error = assertThrows(InputMismatchException.class,()->{
            view.PromptDiscard(controller.game);
        });
        assertThat(((GolfCLITestView)controller.view).output).isEqualTo(
                "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void sendMessageToPlayer() {
        GolfCLITestView view = new GolfCLITestView();
        view.SendMessageToPlayer("This Will Be Output");
        assertThat(view.output).isEqualTo("This Will Be Output\n");
    }
    @Test
    void testDisplayHand(){
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        ((GolfCLITestView)controllerTest.view).input.add("4");
        controllerTest.GameStart();
        ((GolfCLITestView)controllerTest.view).clearVars();
        controllerTest.game.turn = 1;
        controllerTest.view.DisplayHand(controllerTest.game);
        assertThat(((GolfCLITestView)controllerTest.view).output).isEqualTo(
                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"
        );
    }
}