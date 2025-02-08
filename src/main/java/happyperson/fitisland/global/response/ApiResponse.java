package happyperson.fitisland.global.response;

public class ApiResponse<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "요청에 성공했습니다", data);
    }
}
