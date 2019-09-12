package hustlebuddy.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hustlebuddy.models.Exercise;
import hustlebuddy.models.Food;
import hustlebuddy.models.Meal;
import hustlebuddy.models.MuscleGroup;
import hustlebuddy.models.PersonalData;
import hustlebuddy.models.Training;
import hustlebuddy.repositories.FileRepository;

@Service
public class FileService {

	private String defaultProfileImagePath = "src/main/resources/images/profile_images/default.png";
	private String defaultTrainingImagePath = "src/main/resources/images/training_images/default.png";
	private String defaultExerciseImagePath = "src/main/resources/images/exercise_images/default.png";
	private String defaultMuscleGroupImagePath = "src/main/resources/images/muscle_group_images/default.png";
	private String defaultFoodImagePath = "src/main/resources/images/food_images/default.png";
	private String defaultMealImagePath = "src/main/resources/images/meal_images/default.png";
	
	@Autowired
	private FileRepository fileRepository; 

	public void saveProfileImage(MultipartFile file, String fileName, PersonalData personalData) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/profile_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			personalData.setProfileImagePath("images/profile_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultProfileImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/profile_images/" + fileName + defaultProfileImagePath.substring(defaultProfileImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    personalData.setProfileImagePath("images/profile_images/" + fileName + defaultProfileImagePath.substring(defaultProfileImagePath.lastIndexOf(".")));
		}
	}
	
	public void saveTrainingImage(MultipartFile file, String fileName, Training training) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/training_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			training.setTrainingImagePath("images/training_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultTrainingImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/training_images/" + fileName + defaultTrainingImagePath.substring(defaultTrainingImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    training.setTrainingImagePath("images/training_images/" + fileName + defaultTrainingImagePath.substring(defaultTrainingImagePath.lastIndexOf(".")));
		}
	}
	
	public void saveMuscleGroupImage(MultipartFile file, String fileName, MuscleGroup muscleGroup) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/muscle_group_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			muscleGroup.setMuscleGroupImagePath("images/muscle_group_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultMuscleGroupImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/muscle_group_images/" + fileName + defaultMuscleGroupImagePath.substring(defaultMuscleGroupImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    muscleGroup.setMuscleGroupImagePath("images/muscle_group_images/" + fileName + defaultMuscleGroupImagePath.substring(defaultMuscleGroupImagePath.lastIndexOf(".")));
		}
	}
	
	public void saveExerciseImage(MultipartFile file, String fileName, Exercise exercise) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/exercise_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			exercise.setExerciseImagePath("images/exercise_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultExerciseImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/exercise_images/" + fileName + defaultExerciseImagePath.substring(defaultExerciseImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    exercise.setExerciseImagePath("images/exercise_images/" + fileName + defaultExerciseImagePath.substring(defaultExerciseImagePath.lastIndexOf(".")));
		}
	}
	
	public void saveMealImage(MultipartFile file, String fileName, Meal meal) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/meal_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			meal.setMealImagePath("images/meal_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultMealImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/meal_images/" + fileName + defaultMealImagePath.substring(defaultMealImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    meal.setMealImagePath("images/meal_images/" + fileName + defaultMealImagePath.substring(defaultMealImagePath.lastIndexOf(".")));
		}
	}

	public void saveFoodImage(MultipartFile file, String fileName, Food food) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/mp4"))) {
			File convertFile = new File("src/main/resources/images/food_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			food.setFoodImagePath("images/food_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
		else {
			InputStream initialStream = new FileInputStream(new File(defaultFoodImagePath));
		    byte[] buffer = new byte[initialStream.available()];
		    initialStream.read(buffer);
		    File targetFile = new File("src/main/resources/images/food_images/" + fileName + defaultFoodImagePath.substring(defaultFoodImagePath.lastIndexOf(".")));
		    targetFile.createNewFile();
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    initialStream.close();
		    outStream.close();
		    food.setFoodImagePath("images/food_images/" + fileName + defaultFoodImagePath.substring(defaultFoodImagePath.lastIndexOf(".")));
		}
	}
}
