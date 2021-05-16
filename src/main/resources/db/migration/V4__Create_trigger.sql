create or replace function add_to_trig_card()
returns trigger as $$begin
insert into trig_card values('new card was created', now());
return new;
end;$$ language plpgsql;


create trigger trig_card
after insert
on card
for each row execute procedure add_to_trig_card();

