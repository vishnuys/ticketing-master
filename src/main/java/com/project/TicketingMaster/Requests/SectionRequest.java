package com.project.TicketingMaster.Requests;

import com.project.TicketingMaster.Data.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest {

    @NonNull
    private String section;
}
