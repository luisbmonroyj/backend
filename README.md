# backend project
## Backend with Java SpringBoot

This project manages users, roles and access permissions to different url and methods

### Managing Roles

- GET **server/role**: shows all created roles, with name and description
- DELETE **server/role?idRole=<idRole>**: eliminates role according to its id
    *There should be a role to which every user with the deleted role will take as its new role. This one
    is set to "undefined" and cannot be deleted*
- PUT **server/role**: a JSON must be sent with the whole data:
  {"idRole": "<idRole>", "name": "Role Name", "description": "Role description"}
  *The name, the description or both can be changed, as long as the name is not already taken
- POST **server/role**: a JSON must be sent with the following data:
  {"name": "Role Name", "description": "Role description"}
  *idRole is automatically created*
 
### Managing accesses
*Access is a collection that gathers all urls and methods for every endpoint
*The method must be entered in uppercase letters, <METHOD> = {"POST","PUT","PATCH","GET","DELETE"}

- GET **server/access**: shows all created accesses, with url and method
- DELETE **server/access?idAccess=<idAccess>**: eliminates the access according to its id
- PUT **server/access**: a JSON must be sent with the whole data:
  {  "idAccess": "<idAccess>",  "url": "<url>",  "method": "<METHOD>"  } 
- POST **server/access**: a JSON must be sent with the following data:
  { "url":"<url>", "method":"<METHOD>"}
*idAccess is automatically created*
  

### Managing access to roles
*AccessToRole assigns an access (url and method) to a Role, giving permission to do the query

- GET **server/accessToRole**: shows the accesses to every role
- DELETE **server/accessToRole/<idAccessToRole>**: eliminates the accessToRole according to its id
- POST **server/accessToRole?idRole=<idRole>&idAccess=<idAccess>**: sets an access for a role
  *idAtoR is automatically created*
- PUT **server/accessToRole/<id>/role/<idRole>/access/<idAccess>**: changes the access for the role or viceversa

- GET **server/accessToRole/accessValidation/role/<idRole>**: shows the information of the specific <idRole>,
  validating if it has permission to the url and method (Access) entered in the body. The access must be sent in a
  JSON format:
  {  "idAccess": "<idAccess>",  "url": "<url>",  "method": "<METHOD>"  }
  *if the role has this access, it returns the AccessToRole object, with id and so on* 

### Managing users

Users are created with a default role named "undefined".

- GET **server/user**: shows all created users with the encrypted password (SHA256)and the assigned role
- DELETE **server/user?id=<id>**:  eliminates the user according to its id
- PUT **server/user?id=<id>&idRole=<idRole>**: changes the role of the user
- POST & PATCH **server/user**: a JSON must be sent with the whole data:
  {   "id": "<user id>",  "lastname": "<user lastname>",  "name": "<user first name>",
    "email": "<user email>",  "address": "<user address>", "phone": "<user phone number>",
    "username": "<username>",  "password": "<not encrypted password>" }
  *You can change the username, as long as it's not already taken
  *id is NOT automatically created*

