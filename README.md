# Realm Simplified

This projcet shows how to use the realm database in android project with layered way so that developer can use realm database hassle free.

## Getting Started

Just clone or download the code in your matchine. Open in Andorid Studio. You will get the simple application of realm database with all CRUD operation. 
### Prerequisites

Android Studio

### Customize for your use

#### Step 1:-Create Entity and attribute as per your application's need.

```
@RealmClass
public class MyEntity extends RealmObject {
.
.
.
```
*This defined the ORM like data structure. With getter and setter methods.*

#### Step 2:- Create Repository for the entity you defined.

```
public class MyRepo extends Repository<MyEntity> {
    public MyRepo(Context context, Class _aClass) {
        super(context, _aClass);
    }
}
```
*This is the logical layer for database operations where you get the predefined methods. And offcouse you can operride as per your need or add new for specialization perpose.*

##### Your database defining coading is done. Now it's time to use.

#### Step 3:- Initialize your repository, simply by passing context and the Entity.class. 

```
MyRepo myRepo = new MyRepo(this, MyEntity.class);
```

#### Step last:- When ever you want data from realm just ask the repo.

```
//Hey repo create this record
myRepo.save(entity);

//Hey repo update this record
myRepo.saveOrUpdate(entity);

//Hey repo can you please find my record
MyEntity myEntity = (myEntity) myRepo.findById("******");

//Hey repo give me all data
List<MyEntity> myEntities = myRepo.findAll();

//Hey repo please show me what other things you can do
.
.
.

```

## What problem does it solves
Ok, did you find any realm initilization or any boaring try-catch block for each operations in above code.
And what about thread management. You remember transactionBegin(), transactionClose() was everywhere. Want to expand in app database then create two more class and done.
So it's good use it.

## Authors

* **Shrikrishna Ghorpade** 

## Acknowledgments

* Free for all. Edit it, use it.

#### Enjoy Coding


