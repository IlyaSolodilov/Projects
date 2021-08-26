package DichBot;
import java.util.*;
import java.util.regex.*;

public class SimpleBot {

    final String[] COMMON_PHRASES = {
            "Молчание - золото, но иногда можно полезть за словом в кармашек",
            "Иной раз лучше промолчать",
            "Сначала думай, потом говори",
            "Вежливость, лучшее оружие вора",
            "У тебя наверное 100 баллов в ЕГЭ по русскому языку",
            "Многословие зачастую показатель скудного ума",
            "Словами можно обидеть, а извиниться вряд ли",
            "Записывая слова, мы удваиваем их силу",
            "Ясно мыслишь - ясно излагаешь",
            "Что-то ты недоговариваешь..."};
    final String[] ELUSIVE_ANSWERS = {
            "Это сложный вопрос, дай мне подумать",
            "Данной информацией я не владею",
            "Давай не будем об этом?",
            "Это слишком личный вопрос",
            "Не думаю, что тебе понравится ответ",
            "Эх, хотел бы я знать",
            "Ты уверен что хочешь знать?",
            "Я думаю ты и сам знаешь ответ на этот вопрос",
            "Пожалуй это лучше оставить в тайне",
            "Зачем тебе эта информация?"};
    final Map<String, String > PATTERN_FOR_ANALYSIS = new HashMap<String, String>(){{
        //hello
        put ("салют", "hello");
        put ("здрваствуй", "hello");
        put ("хай", "hello");
        put ("привет", "hello");
        put ("ну здорова!", "hello");
        //who
        put ("кто\\s.*ты", "who");
        put ("ты\\s.*кто", "who");
        put ("что\\s.*ты", "who");
        put ("ты\\s.*бот", "who");
        put ("эй\\s.*ты", "who");
        //name
        put ("как\\s.*зовут", "name");
        put ("как\\s.*имя", "name");
        put ("как\\s.*звать", "name");
        put ("какое\\s.*имя", "name");
        put ("есть\\s.*имя", "name");
        //howareyou
        put ("как\\s.*дела", "howareyou");
        put ("как\\s.*жизнь", "howareyou");
        put ("как\\s.*житуха", "howareyou");
        //like
        put ("что\\s.*нравится", "like");
        put ("что\\s.*любишь", "like");
        put ("что\\s.*интересно", "like");
        put ("что\\s.*увлекаешься", "like");
        put ("что\\s.*интересует", "like");
        put ("нравится", "like");
        //do
        put ("что\\s.*делаешь", "do");
        put ("чем\\s.*занят", "do");
        //feel
        put ("кажется","feel");
        put ("чувствую","feel");
        put ("ощущаю","feel");
        put ("испытываю","feel");
        //yes
        put ("^да", "yes");
        put ("согласен", "yes");
        //time
        put ("который\\s.*час", "time");
        put ("сколько\\s.*время", "time");
        //bye
        put ("прощай", "bye");
        put ("пока", "bye");
        put ("покеда", "bye");
        put ("увидимся", "bye");
        //robot
        put("ты\\s.*робот", "robot");
        put("cимфония", "robot");
        put("шедевр", "robot");
        //thank
        put("ты\\s.*классный", "thank");
        put("ты\\s.*крутой", "thank");
        put("ты\\s.*весёлый", "thank");
        put("правда", "thank");
        }};
    final Map<String,String> ANSWERS_BY_PATTERNS = new HashMap<String, String>(){{
        put("hello", "Здравствуй, рад видеть!");
        put("hello", "Приветствую, человек!");
        put("who", "Я бот созданный Илюшой Солодиловым, и я пока еще молод, и неопытен");
        put("name", "Меня зовут Дичь");
        put("name", "Имя нам - Дичь!");
        put("name", "Зови меня - Дичь");
        put("howareyou", "Я в полном порядке");
        put("howareyou", "Неплохо.");
        put("howareyou", "У меня все хорошо!");
        put("like", "Люблю общаться с людьми, и узнавать новое");
        put("like", "Слушать людей, их мысли");
        put("feel", "И давно это у тебя?");
        put("feel", "Я чувствую тоже самое, иногда");
        put("yes", "И я думаю тоже самое");
        put("yes", "В точку!");
        put("bye", "Рад был поболтать. До новых встреч!");
        put("bye", "Покеда");
        put("bye", "Прощай, человек!");
        put("robot", "А вы?");
        put("robot", "Я нет. Но у меня есть могущественные друзья в сфере шахмат, и поисковых систем...");
        put("thank", "Спасибо, я стараюсь!");
        put("thank", "Спасибо, ты тоже ничего");
        put("thank", "Вы мне льстите...");
        put("thank", "Видимо я это заслужил. Спасибо.");
        put("do", "Пока что все что я делаю это общаюсь с тобой");
        put("do", "Я бездельник");
    }};

    Pattern pattern;
    Random random;
    Date date;

    SimpleBot(){
        random = new Random();
        date = new Date();
    }

    String sayInReturn(String msg, boolean ai){
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if(ai){
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String,String> o : PATTERN_FOR_ANALYSIS.entrySet()){
                pattern = Pattern.compile(o.getKey());
                if(pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }

}
