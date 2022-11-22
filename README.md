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

- GET **server/accessToRole/<idAccessToRole>**: shows the information of the specific <idAccessToRole>
- DELETE **server/accessToRole/<idAccessToRole>**: eliminates the accessToRole according to its id
  
- GET **server/accessToRole**: shows the accesses to every role
- POST **server/accessToRole?idRole=<idRole>&idAccess=<idAccess>**: sets an access for a role
  *idAtoR is automatically created*
- PUT **server/accessToRole**: changes the access for the role or viceversa, sending a JSON like this:
  - {"idAccessToRole":"<idAccessToRole>", "idRole":"<idRole>", "idAccess":"<idAccess>"}

### Managing users

Users are created with a default role named "undefined".

- GET **server/user**: shows all created users with the encrypted password (SHA256)and the assigned role
- DELETE **server/user?id=<id>**:  eliminates the user according to its id
- POST & PATCH **server/user**: a JSON must be sent with the whole data:
  {   "id": "<user id>",  "lastname": "<user lastname>",  "name": "<user first name>",
    "email": "<user email>",  "address": "<user address>", "phone": "<user phone number>",
    "username": "<username>",  "password": "<not encrypted password>" }

  *id is not automatically created*

- PUT **server/user/<id>/idRole/<idRole>**: changes the role of the user
