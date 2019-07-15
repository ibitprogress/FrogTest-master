package com.frog.test;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/* Segment Map */
	public static int[][] arrayMap = new int[10][16];

	/* All way */
	public static List<String[]> saveWay = new ArrayList<>();

	public static void main(String[] args) {
		Frog frog = new Frog();

		/*  */
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 16; j++) {
				arrayMap[i][j] = 0;
			}
		}

		frog.setX(10);
		frog.setY(6);

		/* Set frog coordinates */
		arrayMap[frog.getY()][frog.getX()] = 8;

		/* Set tree coordinates */
		arrayMap[8][13] = 3;
		arrayMap[7][4] = 3;

		/* Set finish coordinates */
		arrayMap[9][8] = 7;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(arrayMap[i][j] + "\t");
			}
			System.out.println();
		}
		for (int i = 1; i < 6; i++) {
			jump(frog.getX(), frog.getY(), i, "START(" + frog.getY() + ":" + frog.getX() + ")", 0);
		}

		if (saveWay.size() == 0) {
			System.out.println("No way!");
		} else {

			/* Search minimum way */

			int minimumJump = saveWay.get(0).length;
			String[] minimumWay = saveWay.get(0);

			for (String[] arrayWay : saveWay) {
				if (arrayWay.length < minimumJump) {
					minimumJump = arrayWay.length;
					minimumWay = arrayWay;
				}
			}

			String way = minimumWay[0];
			for (int i = 1; i < minimumWay.length; i++) {
				way += ">" + minimumWay[i];
			}

			System.out.println("Minimum jump:" + --minimumJump);
			System.out.println("Minimum way : " + way);
		}
	}

	public static void jump(int x, int y, int jumpNumber, String way, int jumpCount) {

		String position = "";

		switch (jumpNumber) {
		case 1:
			/* 2 from the center, 1 forward */
			if (y + 2 < 10) {
				if (arrayMap[y + 2][(x + 1) % 16] != 3) {
					y += 2;
					x += 1;

					if (x >= 16) {
						x -= 16;
					}
					position = " > " + y + ":" + x;
				} else {
					position = "Tree";
				}
			} else {
				position = "Left circle";
			}
			break;

		case 2:

			/* 1 from the center, 2 forward */
			if (y + 1 < 10) {
				if (arrayMap[y + 1][(x + 2) % 16] != 3) {
					y += 1;
					x += 2;

					if (x >= 16) {
						x -= 16;
					}
					position = "> " + y + ":" + x;
				} else {
					position = "Tree";
				}
			} else {
				position = "Left circle";
			}
			break;

		case 3:

			/* 3 forward */
			if (arrayMap[y][(x + 3) % 16] != 3) {
				x += 3;

				if (x >= 16) {
					x -= 16;
				}
				position = "> " + y + ":" + x;
			} else {
				position = "Tree";
			}
			break;

		case 4:

			/* 1 to the center, 2 forward */
			if (y - 1 >= 0) {
				if (arrayMap[y - 1][(x + 2) % 16] != 3) {
					y -= 1;
					x += 2;

					if (x >= 16) {
						x -= 16;
					}
					position = "> " + y + ":" + x;
				} else {
					position = "Tree";
				}
			} else {
				position = "Left circle";
			}
			break;
		case 5:

			/* 2 to the center, 1 forward */
			if (y - 2 >= 0) {
				if (arrayMap[y - 2][(x + 1) % 16] != 3) {
					y -= 2;
					x += 1;

					if (x >= 16) {
						x -= 16;
					}
					position = "> " + y + ":" + x;
				} else {
					position = "Tree";
				}
			} else {
				position = "Left circle";
			}
			break;

		default:
			position = "Bad jump";
			break;
		}

		if (!(position.equals("Tree") || position.equals("Left circle") || position.equals("Bad jump"))) {
			if (arrayMap[y][x] == 7) {

				saveWay.add((way + position).split(">"));
				// System.out.println("Finish = " + way + position);
				// System.out.println();

			} else {
				if (jumpCount < 20) {
					for (int i = 1; i < 6; i++) {
						jump(x, y, i, way + position, ++jumpCount);
					}
				}
			}
		}

	}
}
