package it.unical.project.gamemanager;

public class Launcher {

	public static void main(String[] args)
	{
		GameManager gameManager = new GameManager("Brain Dead", 1280, 640);
		gameManager.start();
		
//		System.out.println(gameManager);
//		System.out.println("press a to LEFT");
//		System.out.println("press d to RIGHT");
//		System.out.println("press w to UP");
//			System.out.println("press s to DOWN");
//		System.out.println("press o to SHOOT");
//		try (Scanner scanner = new Scanner(System.in)) {
//			String line = scanner.nextLine();
//			while (!line.equals("e") && (!gameManager.isFine())) {
//				switch (line) {
//					case "a":
//						gameManager.getPlayer().setDirection(Direction.LEFT);
//					break;
//					case "d":
//					gameManager.getPlayer().setDirection(Direction.RIGHT);
//					break;
//					case "w":
//						gameManager.getPlayer().setDirection(Direction.UP);
//						break;
//				case "s":
//						gameManager.getPlayer().setDirection(Direction.DOWN);
//						break;
//					case "o":
//						gameManager.getPlayer().shoot();
//						break;
//					}
//					gameManager.update();
//					System.out.println(gameManager);
//					line = scanner.nextLine();
//				}
//			}
//		}
	}
	}
	

