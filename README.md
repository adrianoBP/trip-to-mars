# trip-to-mars

Programming Applications and Languages (PAPL) Coursework

## Config settings

Path: `src/main/resources/app.config`

Content:

```shell
mongodb.username=<MongoDB Username>
mongodb.password=<MongoDB Password>
mongodb.database=<MongoDB Database>
mongodb.collection.nodes=<MongoDB Nodes Collection>
mongodb.collection.users=<MongoDB Users Collection>
```

## Node composition

### Data definition

#### Node Class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *_id* | `ObjectId` | Node identifier |
| *title* | `String` | Short description of the node |
| *description* | `String` | Main body description of the node |
| *item-to-save* | `String` | Item saved in the user settings once the node is retrieved |
| *is-beginning* | `Boolean` | Indicates if the node is the beginning of the map |
| *options* | <code>List<[Option](#option-class)></code> | List of paths from the node |

#### Option class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *node-id* | `ObjectId` | Identifiers the id of the option node |
| *chance* | `Integer` | Percentage out of 100 indicating the chance of this option being selected |
| *requirements* | <code>List<[Requirement](#requirement-class)></code>  | List of requirements for the option to be available |

#### Requirement class

| Field | Type | Description |
| ----- | ---- | ----------- |
| *name* | `String` | Name of the item to check the validity against |
| *must-exist* | `Boolean` | Indicates if the item has to be present or not for the option to be available |

### Example

```json
{
  "_id": "618bfa303bfb483a60c3792f",
  "title": "A hole!",
  "description": "You discover a hole in a critical section of the spaceship",
  "item-to-save": "",
  "is-beginning": false,
  "options": [
    {
      "node-id": "618bfa303bfb483a60c37930",
      "chance": 0,
      "requirements": [
        {
          "name": "PEN",
          "must-exist": true
        }
      ]
    },
    {
      "node-id": "618bfa303bfb483a60c37932",
      "chance": 0,
      "requirements": [
        {
          "name": "PEN",
          "must-exist": false
        }
      ]
    }
  ]
}
```
