package com.gapharma.data.models;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.gapharma.data.entities.AccessRight;
import com.gapharma.data.entities.User;

public class UserWithAccessRight {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "accessRightId",
            entityColumn = "id"
    )
    public AccessRight accessRight;
}
