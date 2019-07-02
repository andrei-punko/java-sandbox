
## Example of REST service implementation
Based on next tutorial: https://spring.io/guides/tutorials/bookmarks

Service is just POC for IoT purposes and provides possibility to create channels 
and manage channel data items using next endpoints:   
`http://localhost:8989/channels`  
`http://localhost:8989/channels/{channelId}/data`   

Service operations have security restrictions depend on user roles.  
We have one admin and two users with next login/password couples:  
`admin/adminPass`  
`user/userPass`  
`user2/user2Pass`  

