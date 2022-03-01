# trip-to-mars

Programming Applications and Languages (PAPL) Coursework

[Video Demo](https://youtu.be/uoAXjEQZUHQ)

## Config settings

Path (Java): `src/main/resources/app.config`

```shell
filesystem.path.nodes=src/main/resources/node-data.json
filesystem.path.users=src/main/resources/user-data.json
```

Path (Android): `src/main/res/raw/app.config`

```shell
filesystem.path.nodes=node-data.json
filesystem.path.users=user-data.json
```

## Node composition

### Data definition

#### Node Class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *_id* | `UUID` | Node identifier |
| *title* | `String` | Short description of the node |
| *description* | `String` | Main body description of the node |
| *item-to-save* | `String` | Item saved in the user settings once the node is retrieved |
| *is-beginning* | `Boolean` | Indicates if the node is the beginning of the map |
| *options* | <code>List<[Option](#option-class)></code> | List of paths from the node |

#### Option class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *node-id* | `UUID` | Identifiers the id of the option node |
| *chance* | `Integer` | Percentage out of 100 indicating the chance of this option being selected ([see logic](#logic)) |
| *requirements* | <code>List<[Requirement](#requirement-class)></code>  | List of requirements for the option to be available |

#### Requirement class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *name* | `String` | Name of the item to check the validity against |
| *must-exist* | `Boolean` | Indicates if the item has to be present or not for the option to be available |

### Node Document Example

- Path (Java): `src/main/resources/node-data.json`
- Path (Android): `/data/user/0/com.up2037954.triptomars/files/node-data.json`

```json
[
  {
    "_id": "257fc0da-fd2f-4e3b-b084-0c31e62efba3",
    "title": "A hole!",
    "description": "You discover a hole in a critical section of the spaceship",
    "item-to-save": "",
    "is-beginning": false,
    "options": [
      {
        "node-id": "b8056806-12f9-4c13-8d66-610304cdde50",
        "chance": 0,
        "requirements": [
          {
            "name": "PEN",
            "must-exist": true
          }
        ]
      }
    ]
  }
]
```

## Logic

1. IF all the options have a chance != 0, THEN pick a random option based on the chance (**chance based decision**)

2. IF all the options have a chance == 0, THEN display all the options (**user based decision**)

3. IF the option contains requirements OR there's only one available node (story line), THEN pick the node meeting the requirements (**system based decision**)

4. IF there are no options, THEN the end has been reached
