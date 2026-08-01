public class RegistrationAlgorithms {

    public static void mergeSortById(Registration[] registrations, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortById(registrations, left, mid);
        mergeSortById(registrations, mid + 1, right);
        merge(registrations, left, mid, right);
    }

    private static void merge(Registration[] registrations, int left, int mid, int right) {
        Registration[] temp = new Registration[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (registrations[i].getId().compareTo(registrations[j].getId()) <= 0) {
                temp[k] = registrations[i];
                i++;
            } else {
                temp[k] = registrations[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = registrations[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = registrations[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            registrations[left + m] = temp[m];
        }
    }

    public static int binarySearchById(Registration[] sortedRegistrations, String targetId) {
        int left = 0;
        int right = sortedRegistrations.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compareResult = sortedRegistrations[mid].getId().compareTo(targetId);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static Registration sequentialSearchByName(java.util.List<Registration> registrations, String name) {
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getName().equals(name)) {
                return registrations.get(i);
            }
        }
        return null;
    }
}
