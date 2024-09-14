package com.scaler.exambot.models;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {

    private List<Choice> choices;

    // constructors, getters and setters

    public static class Choice {

        private int index;
        private Message message;

        // constructors, getters and setters

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }
}

