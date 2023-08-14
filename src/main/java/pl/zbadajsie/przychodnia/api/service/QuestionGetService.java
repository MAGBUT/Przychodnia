package pl.zbadajsie.przychodnia.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.zbadajsie.przychodnia.api.model.Question;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionGetService {
    private final WebClient webClient;
    private static String apiKey = "vJQ9odftRYNiKc0dnR4VDVU3lEBv0UVaxosYtaAn";

    public Optional<List<Question>> getListQuestion(){
        Mono<Object[]> questionMono = getQuestionMono();
        if(questionMono!=null){
            Object[] objects = questionMono.block();
            ObjectMapper mapper = new ObjectMapper();
            List<Question> collect = Arrays.stream(objects)
                    .map(object -> mapper.convertValue(object, Question.class))
                    .collect(Collectors.toList());
            return Optional.of(collect);
        }
        return Optional.empty();
    }

    private Mono<Object[]> getQuestionMono() {
        try {
            return webClient
                    .get()
                    .uri("/api/v1/questions?apiKey="+apiKey+"&limit=10")
                    .retrieve()
                    .bodyToMono(Object[].class).log();
        } catch (Exception e) {
            return null;
        }
    }
}
