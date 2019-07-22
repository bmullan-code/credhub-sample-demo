# credhub-sample-demo
credhub-sample-demo

## Given a service connection json as follows ...

```
{
  "jdbcUrl": "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_b7d7301eb081996?user=bc6dc450b79d78&password=password",
  "uri": "mysql://bc6dc450b79d78:password@us-cdbr-iron-east-02.cleardb.net:3306/ad_b7d7301eb081996?reconnect=true",
  "name": "ad_b7d7301eb081996",
  "hostname": "us-cdbr-iron-east-02.cleardb.net",
  "port": "3306",
  "username": "bc6dc450b79d78",
  "password": "password"
}
```

## 1. Create a service broker instance
```
cf create-service credhub default cleardb-instance \
 -c '{
  "jdbcUrl": "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_b7d7301eb081996?user=bc6dc450b79d78&password=password",
  "uri": "mysql://bc6dc450b79d78:password@us-cdbr-iron-east-02.cleardb.net:3306/ad_b7d7301eb081996?reconnect=true",
  "name": "ad_b7d7301eb081996",
  "hostname": "us-cdbr-iron-east-02.cleardb.net",
  "port": "3306",
  "username": "bc6dc450b79d78",
  "password": "password"
}'
```

## 2. Bind the service to your app
```
cf bind-service credhub-sample-demo cleardb-instance
cf restage credhub-sample-demo
```

## 3. Add the credhub dependency to pom.xml
```
<dependency>
    <groupId>org.springframework.credhub</groupId>
    <artifactId>spring-credhub-starter</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

## 4. Application environment variables will contain credhub reference
```
"VCAP_SERVICES": {
      "credhub": [
        {
          "label": "credhub",
          "provider": null,
          "plan": "default",
          "name": "cleardb-instance",
          "tags": [
            "credhub"
          ],
          "instance_name": "cleardb-instance",
          "binding_name": "",
          "credentials": {
            "credhub-ref": "/credhub-service-broker/credhub/b2b9c743-696d-44d9-839b-b750548be275/credentials"
          },
          "syslog_drain_url": null,
          "volume_mounts": []
        }
      ]
    }
```

## 5. At runtime credhub will interpolate the credentials.

```
"VCAP_SERVICES":{  
      "credhub":[  
         {  
            "binding_name":"",
            "credentials":{  
               "hostname":"us-cdbr-iron-east-02.cleardb.net",
               "jdbcUrl":"jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_b7d7301eb081996?user=bc6dc450b79d78&password=password",
               "name":"ad_b7d7301eb081996",
               "password":"password",
               "port":"3306",
               "uri":"mysql://bc6dc450b79d78:password@us-cdbr-iron-east-02.cleardb.net:3306/ad_b7d7301eb081996?reconnect=true",
               "username":"bc6dc450b79d78"
            },
            "instance_name":"cleardb-instance",
            "label":"credhub",
            "name":"cleardb-instance",
            "plan":"default",
            "provider":null,
            "syslog_drain_url":null,
            "tags":[  
               "credhub"
            ],
            "volume_mounts":[  


```
