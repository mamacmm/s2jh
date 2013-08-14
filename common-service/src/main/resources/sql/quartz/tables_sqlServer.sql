
create table [dbo].[qrtz_calendars] (
  [calendar_name] [varchar] (200)  not null ,
  [calendar] [image] not null
) on [primary]
go

create table [dbo].[qrtz_cron_triggers] (
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [cron_expression] [varchar] (120)  not null ,
  [time_zone_id] [varchar] (80) 
) on [primary]
go

create table [dbo].[qrtz_fired_triggers] (
  [entry_id] [varchar] (95)  not null ,
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [is_volatile] [varchar] (1)  not null ,
  [instance_name] [varchar] (200)  not null ,
  [fired_time] [bigint] not null ,
  [priority] [integer] not null ,
  [state] [varchar] (16)  not null,
  [job_name] [varchar] (200)  null ,
  [job_group] [varchar] (200)  null ,
  [is_stateful] [varchar] (1)  null ,
  [requests_recovery] [varchar] (1)  null 
) on [primary]
go

create table [dbo].[qrtz_paused_trigger_grps] (
  [trigger_group] [varchar] (200)  not null 
) on [primary]
go

create table [dbo].[qrtz_scheduler_state] (
  [instance_name] [varchar] (200)  not null ,
  [last_checkin_time] [bigint] not null ,
  [checkin_interval] [bigint] not null
) on [primary]
go

create table [dbo].[qrtz_locks] (
  [lock_name] [varchar] (40)  not null 
) on [primary]
go

create table [dbo].[qrtz_job_details] (
  [job_name] [varchar] (200)  not null ,
  [job_group] [varchar] (200)  not null ,
  [description] [varchar] (250) null ,
  [job_class_name] [varchar] (250)  not null ,
  [is_durable] [varchar] (1)  not null ,
  [is_volatile] [varchar] (1)  not null ,
  [is_stateful] [varchar] (1)  not null ,
  [requests_recovery] [varchar] (1)  not null ,
  [job_data] [image] null
) on [primary]
go

create table [dbo].[qrtz_job_listeners] (
  [job_name] [varchar] (200)  not null ,
  [job_group] [varchar] (200)  not null ,
  [job_listener] [varchar] (200)  not null
) on [primary]
go

create table [dbo].[qrtz_simple_triggers] (
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [repeat_count] [bigint] not null ,
  [repeat_interval] [bigint] not null ,
  [times_triggered] [bigint] not null
) on [primary]
go

create table [dbo].[qrtz_blob_triggers] (
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [blob_data] [image] null
) on [primary]
go

create table [dbo].[qrtz_trigger_listeners] (
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [trigger_listener] [varchar] (200)  not null
) on [primary]
go

create table [dbo].[qrtz_triggers] (
  [trigger_name] [varchar] (200)  not null ,
  [trigger_group] [varchar] (200)  not null ,
  [job_name] [varchar] (200)  not null ,
  [job_group] [varchar] (200)  not null ,
  [is_volatile] [varchar] (1)  not null ,
  [description] [varchar] (250) null ,
  [next_fire_time] [bigint] null ,
  [prev_fire_time] [bigint] null ,
  [priority] [integer] null ,
  [trigger_state] [varchar] (16)  not null ,
  [trigger_type] [varchar] (8)  not null ,
  [start_time] [bigint] not null ,
  [end_time] [bigint] null ,
  [calendar_name] [varchar] (200)  null ,
  [misfire_instr] [smallint] null ,
  [job_data] [image] null
) on [primary]
go

alter table [dbo].[qrtz_calendars] with nocheck add
  constraint [pk_qrtz_calendars] primary key  clustered
  (
    [calendar_name]
  )  on [primary]
go

alter table [dbo].[qrtz_cron_triggers] with nocheck add
  constraint [pk_qrtz_cron_triggers] primary key  clustered
  (
    [trigger_name],
    [trigger_group]
  )  on [primary]
go

alter table [dbo].[qrtz_fired_triggers] with nocheck add
  constraint [pk_qrtz_fired_triggers] primary key  clustered
  (
    [entry_id]
  )  on [primary]
go

alter table [dbo].[qrtz_paused_trigger_grps] with nocheck add
  constraint [pk_qrtz_paused_trigger_grps] primary key  clustered
  (
    [trigger_group]
  )  on [primary]
go

alter table [dbo].[qrtz_scheduler_state] with nocheck add
  constraint [pk_qrtz_scheduler_state] primary key  clustered
  (
    [instance_name]
  )  on [primary]
go

alter table [dbo].[qrtz_locks] with nocheck add
  constraint [pk_qrtz_locks] primary key  clustered
  (
    [lock_name]
  )  on [primary]
go

alter table [dbo].[qrtz_job_details] with nocheck add
  constraint [pk_qrtz_job_details] primary key  clustered
  (
    [job_name],
    [job_group]
  )  on [primary]
go

alter table [dbo].[qrtz_job_listeners] with nocheck add
  constraint [pk_qrtz_job_listeners] primary key  clustered
  (
    [job_name],
    [job_group],
    [job_listener]
  )  on [primary]
go

alter table [dbo].[qrtz_simple_triggers] with nocheck add
  constraint [pk_qrtz_simple_triggers] primary key  clustered
  (
    [trigger_name],
    [trigger_group]
  )  on [primary]
go

alter table [dbo].[qrtz_trigger_listeners] with nocheck add
  constraint [pk_qrtz_trigger_listeners] primary key  clustered
  (
    [trigger_name],
    [trigger_group],
    [trigger_listener]
  )  on [primary]
go

alter table [dbo].[qrtz_triggers] with nocheck add
  constraint [pk_qrtz_triggers] primary key  clustered
  (
    [trigger_name],
    [trigger_group]
  )  on [primary]
go

alter table [dbo].[qrtz_cron_triggers] add
  constraint [fk_qrtz_cron_triggers_qrtz_triggers] foreign key
  (
    [trigger_name],
    [trigger_group]
  ) references [dbo].[qrtz_triggers] (
    [trigger_name],
    [trigger_group]
  ) on delete cascade
go

alter table [dbo].[qrtz_job_listeners] add
  constraint [fk_qrtz_job_listeners_qrtz_job_details] foreign key
  (
    [job_name],
    [job_group]
  ) references [dbo].[qrtz_job_details] (
    [job_name],
    [job_group]
  ) on delete cascade
go

alter table [dbo].[qrtz_simple_triggers] add
  constraint [fk_qrtz_simple_triggers_qrtz_triggers] foreign key
  (
    [trigger_name],
    [trigger_group]
  ) references [dbo].[qrtz_triggers] (
    [trigger_name],
    [trigger_group]
  ) on delete cascade
go

alter table [dbo].[qrtz_trigger_listeners] add
  constraint [fk_qrtz_trigger_listeners_qrtz_triggers] foreign key
  (
    [trigger_name],
    [trigger_group]
  ) references [dbo].[qrtz_triggers] (
    [trigger_name],
    [trigger_group]
  ) on delete cascade
go

alter table [dbo].[qrtz_triggers] add
  constraint [fk_qrtz_triggers_qrtz_job_details] foreign key
  (
    [job_name],
    [job_group]
  ) references [dbo].[qrtz_job_details] (
    [job_name],
    [job_group]
  )
go

insert into [dbo].[qrtz_locks] values('trigger_access');
insert into [dbo].[qrtz_locks] values('job_access');
insert into [dbo].[qrtz_locks] values('calendar_access');
insert into [dbo].[qrtz_locks] values('state_access');
insert into [dbo].[qrtz_locks] values('misfire_access');
