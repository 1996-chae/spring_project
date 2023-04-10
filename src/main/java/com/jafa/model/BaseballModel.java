package com.jafa.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jafa.domain.BaseballGameVO;

@Service
public class BaseballModel {

    public BaseballGameVO playRound(BaseballGameVO vo) {

        int strikes = countStrikes(vo);
        int balls = countBalls(vo);

        String result = vo.getGuess() + " -> " + strikes + "S " + balls + "B";
        List<String> results = vo.getResults();
        results.add(result);
        vo.setResults(results);
        if (strikes == vo.getNUMBER_LENGTH()) {
            vo.setGameOver(true);
            return vo;
        } else if (vo.getRound() == vo.getMAX_ROUNDS()) {
        	vo.setGameOver(true);
            results.add("게임 오버. 정답은 " + vo.getAnswer() + "입니다.");
            return vo;
        }
        vo.setRound(vo.getRound()+1);
        return vo;
    }

    private int countStrikes(BaseballGameVO vo) {
        int count = 0;
        String answer = vo.getAnswer();
        String guess = vo.getGuess();
        for (int i = 0; i < vo.getNUMBER_LENGTH(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private int countBalls(BaseballGameVO vo) {
        int count = 0;
        String guess = vo.getGuess();
        String answer = vo.getAnswer();
        for (int i = 0; i < vo.getNUMBER_LENGTH(); i++) {
            if (guess.charAt(i) != answer.charAt(i) && answer.indexOf(guess.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    // 랜덤 번호 지정
    public String generateRandomNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(numbers.get(i));
        }
        return sb.toString();
    }
}
