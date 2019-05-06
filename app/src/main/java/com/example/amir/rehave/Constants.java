package com.example.amir.rehave;

public class Constants {
    public enum Section {
        ADDICTION(1), PROTECTION(2), ARCHIVE(3), ALL(0);
        private int section;

        Section(int section) {
            this.section = section;
        }

        public int toInt() {
            return section;
        }
    }
}
