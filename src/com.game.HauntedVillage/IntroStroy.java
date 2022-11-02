package com.game.HauntedVillage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;

class IntroStroy {
    //prints game background information before game
    void presentInfo() {
        Art.showArt("house");
        try (JsonParser jParser = new JsonFactory()
                .createParser(new File("resources/info.json"))) {

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();

                if ("story".equals(fieldname)) {
                    jParser.nextToken();
                    System.out.println(jParser.getText());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
