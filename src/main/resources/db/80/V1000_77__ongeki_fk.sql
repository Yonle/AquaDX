alter table ongeki_user_event_map drop foreign key FKU_ONGEKI_USER_EVENT_MAP;
alter table ongeki_user_skin drop foreign key FKU_ONGEKI_USER_SKIN;

alter table ongeki_user_event_map add constraint FKU_ONGEKI_USER_EVENT_MAP
    foreign key (user_id) references ongeki_user_data (id)
        on delete cascade on update cascade;

alter table ongeki_user_skin add constraint FKU_ONGEKI_USER_SKIN
    foreign key (user_id) references ongeki_user_data (id)
        on delete cascade on update cascade;