import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Введите данные игроков: (Введите !СТОП для конца ввода)\n");
        int count = 1;
        ArrayList<Player> players = new ArrayList<Player>();
        while (true) {
            System.out.println("Введите имя " + String.valueOf(count) + "-го игрока");
            String name = console.next();
            if (name.compareTo("!СТОП") == 0) {
                System.out.println("Ввод игроков окончен!");
                break;
            }
            System.out.println("Введите символ которым будет играть " + name);
            String symbol = console.next();
            if (symbol.compareTo("!СТОП") == 0) {
                System.out.println("Ввод игроков окончен!");
                break;
            }
            players.add(new Player(name, symbol));
            count++;
        }
        GameState state = new GameState();
        System.out.println("Хотите подключить бота? Если да - то напишите !БОТ");
        String message = console.next();
        if (message.compareTo("!БОТ") == 0) {
            System.out.println("Теперь против вас будет играть бот!");
            System.out.println("Введите как его будут звать");
            String name = console.next();
            System.out.println("Введите символ за который он будет играть");
            String symbol = console.next();
            players.add(new Bot(name,symbol, state));
        }
        else System.out.println("Ну не хотите - как хотите :D");
        System.out.println("\nВведите размеры поля");
        state.Start(console.nextInt(), players);
        int i = 0;
        while (true) {
            if (state.IsAnyoneWin) break;
            Player currentPlayer = players.get(i);
            if (currentPlayer instanceof Bot) {
                ((Bot) currentPlayer).GetSmartMove();
                System.out.println("Ходит " + currentPlayer.getName() + " !");
                state.getMap().DisplayOnTheScreen();
            }
            else {
                System.out.println("Ходит " + currentPlayer.getName() + "!");
                System.out.println("Введите координаты по горионтали куда хотите сходить");
                int positionX = console.nextInt();
                System.out.println("Введите координаты по вертикали куда хотите сходить");
                int positionY = console.nextInt();
                if (positionX < state.getSize() && positionY < state.getSize() && state.getMap().IsCellEmpty(positionX,positionY)) {
                    currentPlayer.GetMove(positionX, positionY, state);
                    state.getMap().DisplayOnTheScreen();
                }
                else {
                    System.out.println("В данной клетке уже стоит другой символ или вы выбрали клетку за пределами поля! Попробуйте сделать ход в другую клетку");
                    continue;
                }
            }
            i++;
            if (i == players.stream().count()) i = 0;
        }
    }
}
