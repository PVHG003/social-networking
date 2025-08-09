# UserControllerApi

All URIs are relative to *http://localhost:8080*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getProfile**](#getprofile) | **GET** /api/users/{handleName} | |
|[**getUserPosts**](#getuserposts) | **GET** /api/users/{handleName}/posts | |
|[**updateProfile**](#updateprofile) | **PUT** /api/users/me | |

# **getProfile**
> ApiResponseProfileResponse getProfile()


### Example

```typescript
import {
    UserControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new UserControllerApi(configuration);

let handleName: string; // (default to undefined)

const { status, data } = await apiInstance.getProfile(
    handleName
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseProfileResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getUserPosts**
> ApiResponseListPostResponse getUserPosts()


### Example

```typescript
import {
    UserControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new UserControllerApi(configuration);

let handleName: string; // (default to undefined)

const { status, data } = await apiInstance.getUserPosts(
    handleName
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **handleName** | [**string**] |  | defaults to undefined|


### Return type

**ApiResponseListPostResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updateProfile**
> ApiResponseProfileResponse updateProfile()


### Example

```typescript
import {
    UserControllerApi,
    Configuration,
    ProfileRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new UserControllerApi(configuration);

let data: ProfileRequest; // (default to undefined)
let file: File; // (optional) (default to undefined)

const { status, data } = await apiInstance.updateProfile(
    data,
    file
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **data** | **ProfileRequest** |  | defaults to undefined|
| **file** | [**File**] |  | (optional) defaults to undefined|


### Return type

**ApiResponseProfileResponse**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

