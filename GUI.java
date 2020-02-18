import javax.swing.*;
import java.io.File;

public class GUI {

    JFrame frame; // a good old frame to contain it all


    JPanel panel;
    JScrollPane scroll;


    JButton[] cardChoices; // buttons to choose which card
    JButton btnInstructions; // button for instructions
    JTextArea txtMoves; // contains all the game play
    final static int numCards = 3; // an integer that will hold the number of cards

    public GUI() {
        frame = new JFrame("CHIMP!!");
        panel = new JPanel(); // builds the frame and panel

        btnInstructions = new JButton("HELP"); // a help button that opens up the pdf to the rules
        txtMoves = new JTextArea();
        txtMoves.append("Welcome to CHIMP!\n\n");

        frame.setSize(570, 500); // setting some sizes and visibility
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes program once frame is clicked out
        frame.add(panel); // adds the panel that contains everything else

        panel.setLayout(null); // set the layout to be null

        cardChoices = new JButton[3];
        for (int i = 0; i < 3; i++) {
            cardChoices[i] = new JButton((i + 1) + ""); // makes the text, 1, 2, 3 for card choices
            final int indexed = i;

            cardChoices[i].addActionListener(e -> { // adds in an action listener, if clicked, does something
                Chimp.nextPlayer(indexed);
            });

            panel.add(cardChoices[i]); // adds them in and then puts them
            cardChoices[i].setBounds(140 + 100 * i, 350, 100, 50);
        }

        JScrollPane scrollableTextArea = new JScrollPane(txtMoves);

        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollableTextArea.setBounds(50, 50, 470, 300); // adds in a text area that contains the moves of the game
        txtMoves.setEditable(false); // sets the editing off
        txtMoves.setLineWrap(true);

        panel.add(scrollableTextArea);

        btnInstructions.addActionListener(e -> { // if pressed, then it opens the file
            try {
                if ((new File("C:\\Users\\Eric\\eclipse-workspace\\Chimp\\src\\CHIMP instructions.pdf")).exists()) {

                    Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Eric\\eclipse-workspace\\Chimp\\src\\CHIMP instructions.pdf");
                    p.waitFor();

                } else {

                    System.out.println("File is not exists");

                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });

        btnInstructions.setBounds(240, 400, 100, 50);
        panel.add(btnInstructions);

    }


}
