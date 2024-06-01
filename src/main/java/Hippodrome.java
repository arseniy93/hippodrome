import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
public class Hippodrome {

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            log.error(" Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            log.error(" Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }
        log.info(" Начало скачек. Количество участников:{}",horses.size());

        this.horses = horses;
        log.debug("Создание Hippodrome, лошадей [{}]",horses.size());
    }


    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        Horse horseWin=horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
        log.info("Окончание скачек. Победитель: {}",horseWin.getName());
        return horseWin;
    }
}
