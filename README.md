### JBoss EAP 7.4.x Jakarta EE sample app

```
rm -fr sample-jakartaEE8.ear ; mvn clean package -P full-build
cp module-ear/target/sample-jakartaEE8.ear .
```

### JBoss EAP cleanup
```
rm -fr $JBOSS_HOME/standalone/data/content/*
rm -fr $JBOSS_HOME/standalone/tmp/*
rm -fr $JBOSS_HOME/standalone/deployments/*
```

### JBoss EAP deployment
```
cp sample-jakartaEE8.ear $JBOSS_HOME/standalone/deployments
```
Then, open web browser at: http://localhost:8080/sample

known issues:<br>
https://access.redhat.com/solutions/3906401
https://issues.redhat.com/browse/WFLY-10655?focusedCommentId=13635942&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#comment-13635942

docs:<br>
https://jakarta.ee/specifications/cdi/2.0/cdi-spec-2.0.html#part_3
https://dev.to/otumianempire/change-branch-name-from-master-to-main-50ei
