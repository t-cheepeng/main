package seedu.exercise.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.exercise.commons.core.LogsCenter;
import seedu.exercise.commons.exceptions.DataConversionException;
import seedu.exercise.model.ReadOnlyResourceBook;
import seedu.exercise.model.ReadOnlyUserPrefs;
import seedu.exercise.model.UserPrefs;
import seedu.exercise.model.property.PropertyManager;
import seedu.exercise.model.resource.Exercise;
import seedu.exercise.model.resource.Regime;
import seedu.exercise.model.resource.Schedule;
import seedu.exercise.storage.bookstorage.ResourceBookStorage;

/**
 * Manages storage of ExerHealth data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ResourceBookStorage<Exercise> exerciseBookStorage;
    private ResourceBookStorage<Exercise> exerciseDatabase;
    private ResourceBookStorage<Regime> regimeBookStorage;
    private ResourceBookStorage<Schedule> scheduleBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PropertyManagerStorage propertyManagerStorage;


    public StorageManager(ResourceBookStorage<Exercise> exerciseBookStorage,
                          ResourceBookStorage<Exercise> exerciseDatabase,
                          ResourceBookStorage<Regime> regimeBookStorage,
                          ResourceBookStorage<Schedule> scheduleBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          PropertyManagerStorage propertyManagerStorage) {
        super();
        this.exerciseBookStorage = exerciseBookStorage;
        this.exerciseDatabase = exerciseDatabase;
        this.regimeBookStorage = regimeBookStorage;
        this.scheduleBookStorage = scheduleBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.propertyManagerStorage = propertyManagerStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ExerciseBook methods ==============================

    @Override
    public Path getExerciseBookFilePath() {
        return exerciseBookStorage.getResourceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyResourceBook<Exercise>> readExerciseBook() throws DataConversionException, IOException {
        return readExerciseBook(exerciseBookStorage.getResourceBookFilePath());
    }

    @Override
    public Optional<ReadOnlyResourceBook<Exercise>> readExerciseBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return exerciseBookStorage.readResourceBook(filePath);
    }

    @Override
    public void saveExerciseBook(ReadOnlyResourceBook<Exercise> exerciseBook) throws IOException {
        saveExerciseBook(exerciseBook, exerciseBookStorage.getResourceBookFilePath());
    }

    @Override
    public void saveExerciseBook(ReadOnlyResourceBook<Exercise> exerciseBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        exerciseBookStorage.saveResourceBook(exerciseBook, filePath);
    }

    // ================ ExerciseDatabase methods ==============================

    @Override
    public Path getExerciseDatabaseFilePath() {
        return exerciseDatabase.getResourceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyResourceBook<Exercise>> readExerciseDatabase()
        throws DataConversionException, IOException {
        return readExerciseBook(exerciseDatabase.getResourceBookFilePath());
    }

    @Override
    public Optional<ReadOnlyResourceBook<Exercise>> readExerciseDatabase(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return exerciseDatabase.readResourceBook();
    }

    //===============RegimeBook methods=============================================
    @Override
    public Path getRegimeBookFilePath() {
        return regimeBookStorage.getResourceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyResourceBook<Regime>> readRegimeBook() throws DataConversionException, IOException {
        return readRegimeBook(regimeBookStorage.getResourceBookFilePath());
    }

    @Override
    public Optional<ReadOnlyResourceBook<Regime>> readRegimeBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return regimeBookStorage.readResourceBook(filePath);
    }

    @Override
    public void saveRegimeBook(ReadOnlyResourceBook<Regime> regimeBook) throws IOException {
        saveRegimeBook(regimeBook, regimeBookStorage.getResourceBookFilePath());
    }

    @Override
    public void saveRegimeBook(ReadOnlyResourceBook<Regime> regimeBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        regimeBookStorage.saveResourceBook(regimeBook, filePath);
    }

    //===============ScheduleBook methods=========================================
    @Override
    public Path getScheduleBookFilePath() {
        return scheduleBookStorage.getResourceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyResourceBook<Schedule>> readScheduleBook() throws DataConversionException, IOException {
        return readScheduleBook(getScheduleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyResourceBook<Schedule>> readScheduleBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleBookStorage.readResourceBook(filePath);
    }

    @Override
    public void saveScheduleBook(ReadOnlyResourceBook<Schedule> scheduleBook) throws IOException {
        saveScheduleBook(scheduleBook, getScheduleBookFilePath());
    }

    @Override
    public void saveScheduleBook(ReadOnlyResourceBook<Schedule> scheduleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleBookStorage.saveResourceBook(scheduleBook, filePath);
    }

    // ================ PropertyManager methods ==============================
    @Override
    public Path getPropertyManagerFilePath() {
        return propertyManagerStorage.getPropertyManagerFilePath();
    }

    @Override
    public Optional<PropertyManager> readPropertyManager() throws DataConversionException, IOException {
        return readPropertyManager(propertyManagerStorage.getPropertyManagerFilePath());
    }

    @Override
    public Optional<PropertyManager> readPropertyManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return propertyManagerStorage.readPropertyManager(filePath);
    }

    @Override
    public void savePropertyManager(PropertyManager propertyManager) throws IOException {
        savePropertyManager(propertyManager, propertyManagerStorage.getPropertyManagerFilePath());
    }

    @Override
    public void savePropertyManager(PropertyManager propertyManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        propertyManagerStorage.savePropertyManager(propertyManager, filePath);
    }

}
