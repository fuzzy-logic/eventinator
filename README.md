
# Adventures in CQRS


## For example useage of this project see GenericEventProcessorIntegrationTest

### SETUP

1.) This project needs to be able to persist Event objects. By default in eventinator-context.xml mongodb is used
with spring data. If you are happy to use this setup then just import eventinator-context.xml to your spring application.
If you wish to use another event datastore then you may be able to at your own risk, but your implementation must
implement the EventRepository interface and be defined as a spring bean to be autowired. This is probably not reccomended
as a schemaless mongodb is used to dynamically store any Event subclasses which can have different fields.

2.) Have domain repositories that are named '<DomainObjectClassName>Repository' E.G: For a domain object
'Customer' eventinator expects to find a spring bean called 'CustomerRepository' that MUST implement the CrudRepository
interface which is defined as part of spring data.

3.) Create an Event object that extends AbstractEvent which define the data needed to alter a domain object

4.) Create a Command Object that extends AbstractCommand, this object will use the data in the event to
alter any domain object and you must implement the 'mutating' code yourself in this class

5.) Import the EventHandler bean in to your client class (such a a controller)

6.) annotate your domain object's id and version fields with @DomainId and @DomainVersion  so the framework can
automatically increment the version number, and obtain the domain object's ID for persistence retrieval.

7.) Send event objects from your client class


