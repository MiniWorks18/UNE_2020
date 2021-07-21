/*
 * An implementation of spn scheduling.
 * Author: Tully McDonald (tmcdon26@myune.edu.au)
 */

/* The process details we're interested in for the spn algorithm.*/
typedef struct spn_process {
  unsigned int pid;
  unsigned int processing_time;
  unsigned int arrival_time;
  unsigned int processed_time;
  struct spn_process *next_process;
} spn_process;

/* The list of all processes we know about.*/
spn_process process_list = {0, 0, 0, 0, NULL};

/*
 * Adds the given process to the ready queue, indicating it is ready to be scheduled.
 * Keeps a list of all processes, sorted by time remaining, then pid.
 * parameters:
 *   process - the process to add to the ready queue
 */
void add_to_ready_queue(const process_initial process) {
  // Construct the new spn_process
  spn_process *new_process = malloc(sizeof(spn_process));
  new_process->pid = process.pid;
  new_process->processing_time = process.processing_time;
  new_process->arrival_time = process.arrival_time;
  new_process->processed_time = 0;
  new_process->next_process = NULL;

  // Determine where in the queue it should be added
  spn_process *end = &process_list;
  // Skip past processes with a shorter remaining time
  while (end->next_process && ((end->next_process->processing_time - end->next_process->processed_time) < (new_process->processing_time - new_process->processed_time))) {
    end = end->next_process;
  }
  // Skip past processes with the same remianing time but a bigger PID
  while (end->next_process && end->next_process->arrival_time == new_process->arrival_time && end->next_process->pid > new_process->pid) {
    end = end->next_process;
  }

  // Add new process to the queue in the correct location
  new_process->next_process = end->next_process;
  end->next_process = new_process;
}

/*
 * Determines the next process to the scheduled.
 * Implementes spn, meaning it will select the process with the shortest remaining time
 * that has not yet completed.
 * If two processes have the same remaining time, it will select the one with the biggest PID first.
 * returns:
 *   The PID of the process to be scheduled next, or 0 if no process should be scheduled
 */
unsigned int get_next_scheduled_process() {
  spn_process *node = &process_list;
  // While there are still processes to check
  while (node->next_process) {
    spn_process *next = node->next_process;

    // If the next process needs more processing time, schedule it
    if (next->processed_time < next->processing_time) {
      int pid = next->pid;
      next->processed_time++;
      // Check if the process has finished
      if (next->processed_time == next->processing_time) {
        // If so, remove it from the list
        node->next_process = next->next_process;
        free(next);
      }
      return pid;
      
    }

    // Move to the next process
    node = node->next_process;
  }
  return 0;
}

