REGISTER /usr/local/pig/piggybank.jar;

REGISTER /usr/local/pig/mysql-connector-java-5.1.17-bin.jar;

SET default_parallel 8;

SET job.name 'Aggregate-Question Resource play';

HOURLY_EVENTS = LOAD 'cassandra://$KEYSPACE/agg_event_resource_user_hour' USING CassandraStorage() ;

HOURLY_VALUES = FOREACH HOURLY_EVENTS GENERATE date_id,event_id,user_uid,gooru_oid,parent_gooru_oid, organization_uid,attempt_first_status_count.value as attempt_first_status_count,attempt_first_status;

DUPLICATE_HOURYLY_VALUES = DISTINCT HOURLY_VALUES;

LIMIT_GROUP_VALUES = LIMIT DUPLICATE_HOURYLY_VALUES 100;

CQRP_HOURLY_EVENTS = FILTER DUPLICATE_HOURYLY_VALUES  BY (event_id.value == 224);

GROUPED_HOURLY_VALUES = GROUP CQRP_HOURLY_EVENTS by (gooru_oid,parent_gooru_oid,attempt_first_status,organization_uid);

FLATTENED_VALUES = FOREACH GROUPED_HOURLY_VALUES GENERATE StringConcat((group.gooru_oid.value is null ? 'NA':group.gooru_oid.value),'-',(group.organization_uid.value is null ? 'NA':group.organization_uid.value)) as KEY, FLATTEN(CQRP_HOURLY_EVENTS) , SUM(CQRP_HOURLY_EVENTS.attempt_first_status_count) as attempt_first_status_count;

FILTER_FLATTENED_VALUES = FILTER FLATTENED_VALUES BY attempt_first_status.value is not null AND parent_gooru_oid.value is not null;

AGG_HOURLY_VALUES = FOREACH FILTER_FLATTENED_VALUES GENERATE StringConcat('R~',parent_gooru_oid.value,'~',gooru_oid.value) as key,TOTUPLE('gooru_oid',gooru_oid.value) as gooru_oid,TOTUPLE(StringConcat('CP~Q~all~',attempt_first_status.value),attempt_first_status_count) as attempt_first_status_count;

FILETR_VALID_HOUR_VALUES = FILTER AGG_HOURLY_VALUES BY key is not null;

STORE FILETR_VALID_HOUR_VALUES INTO 'cassandra://$KEYSPACE/agg_event_collection_resource' USING CassandraStorage();
