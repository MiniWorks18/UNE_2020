#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LEN 50

/* Monitors for child return value and continues once complete */
void checkChild(char name[], int *status, pid_t *id) {

	if (-1 == waitpid(*id, status, 0)) {
			printf("No child process to wait for!");
		} else if (WIFEXITED(*status)) {
			*status = WEXITSTATUS(*status);
			printf("%s evaluated to: %d\n", name, *status);
		} else {
			printf("%s exited abnormally!\n", name);
		}
}

/* Accepts plus/multiply expressions and calculates the answer */
int computeExpression(char exp[]) {
	int total = 0;
	int term = 0;

	for (int i = 0; i < strlen(exp); i++) {
		if (exp[i] == '+') {
			total += term;
			term = 0;
		} else if (exp[i] == '*') {
			if (term == 0)
				term = 1;
			i++;
			term *= exp[i]-48; // Switching from ASCII to it's numerical value
		} else {
			term = exp[i]-48;
		}
	}
	return total+term;
}

int main() {
	int numStatus, denStatus, answer;
	pid_t numChild, denChild;
	char numerator[MAX_LEN]; // = "1+2*3+9"; // = 16
	char denominator[MAX_LEN]; // = "2+3*3+1"; // = 12
	float div;
	char division[MAX_LEN];
	int slashFound = 0;

	printf("Enter a division calculation: ");
	scanf("%s", division);

	// Validation 
	/* Side note: Attempted to place this in it's own function, 
	 * but I had trouble passing the char array by reference
	 */
	for (int i = 0; i < strlen(division); i++) {
		if (division[i] == '+' || division[i] == '*' || 
			(division[i] <= 57 && division[i] >= 48)) {
			// Valid use of numerous "0-9", "+", or "*"
		} else if (division[i] == '/') {
			// There should only ever be one "/" in the expression
			if (slashFound) {
				printf("Invalid expression! Please use only one slash.\n");
				return 0;
			}
			slashFound = 1;
		} else {
			printf("Invalid expression! Please use a single slash (/), only numerical characters, plus (+) and multiply (*)\n");
			return 0;
		}
	}
	if (!slashFound) {
		printf("Invalid expression! Please use a slash as the division character.\n");
		return 0;
	}

	// Splits the numerator from the denominator
	strcpy(numerator, strtok(division, "/"));
	strcpy(denominator, strtok(NULL, "/"));

	// Forks the numerator child
	numChild = fork();

	if (numChild) {
		// Parent code for numerator
		checkChild("Numerator", &numStatus, &numChild);
		// Forks the denominator child
		denChild = fork();
		if (denChild) {
			// Parent code for denominator
			checkChild("Denominator", &denStatus, &denChild);
		} else {
			// denChild code
			answer = computeExpression(denominator);
			if (answer < 1 || answer > 255) {
				printf("Bad denominator result. Should be 1-255, found: %d\n", answer);
				return 0;
			}
			return answer;
		}
	} else {
		// numChild code
		answer = computeExpression(numerator);
		if (answer < 1 || answer > 255) {
			printf("Bad numerator result. Should be 1-255, found: %d\n", answer);
			return 0;
		}
		return answer;
	}

	// Result validation
	if (numStatus < 1 || denStatus < 1) 
		return 0;

	div = (float) numStatus / denStatus;
	printf("Result: %d / %d = %.2f\n", numStatus, denStatus, div);

	return 0;
}