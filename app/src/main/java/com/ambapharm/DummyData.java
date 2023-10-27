package com.ambapharm;

import com.ambapharm.data.models.IssueCard;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static List<IssueCard> generateDummyData() {
        List<IssueCard> issueCard = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            issueCard.add(new IssueCard(
                    "Medicine " + i,
                    String.valueOf(i),
                    "Problem Type " + i,
                    "Description for Medicine " + i
            ));
        }
        return issueCard;
    }
}
