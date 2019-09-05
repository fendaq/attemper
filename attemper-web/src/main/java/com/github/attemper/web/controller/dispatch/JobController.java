package com.github.attemper.web.controller.dispatch;

import com.github.attemper.common.constant.APIPath;
import com.github.attemper.common.param.dispatch.job.*;
import com.github.attemper.common.result.CommonResult;
import com.github.attemper.common.result.app.project.Project;
import com.github.attemper.common.result.dispatch.job.Job;
import com.github.attemper.common.result.dispatch.job.JobWithVersionResult;
import com.github.attemper.core.service.dispatch.JobService;
import com.github.attemper.web.service.dispatch.JobOperatedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api("Job")
@RestController
public class JobController {
	
	@Autowired
	private JobService jobService;

	@Autowired
	private JobOperatedService jobOperatedService;

	@ApiOperation("List jobs")
	@ApiImplicitParam(value = "JobListParam", name = "param", dataType = "JobListParam", required = true)
	@GetMapping(APIPath.JobPath.$)
	public CommonResult<Map<String, Object>> list(JobListParam param) {
		return CommonResult.putResult(jobOperatedService.list(param));
	}

	@ApiOperation("Add job")
	@ApiImplicitParam(value = "JobSaveParam", name = "param", dataType = "JobSaveParam", required = true)
	@PostMapping(APIPath.JobPath.$)
	public CommonResult<Job> add(@RequestBody JobSaveParam param) {
		return CommonResult.putResult(jobOperatedService.add(param));
	}

	@ApiOperation("Update job")
	@ApiImplicitParam(value = "JobSaveParam", name = "param", dataType = "JobSaveParam", required = true)
	@PutMapping(APIPath.JobPath.$)
	public CommonResult<Job> update(@RequestBody JobSaveParam param) {
		return CommonResult.putResult(jobOperatedService.update(param));
	}

	@ApiOperation("Update job content")
	@ApiImplicitParam(value = "JobContentSaveParam", name = "param", dataType = "JobContentSaveParam", required = true)
	@PutMapping(APIPath.JobPath.CONTENT)
	public CommonResult<JobWithVersionResult> updateContent(@RequestBody JobContentSaveParam param) {
		return CommonResult.putResult(jobOperatedService.updateContent(param));
	}

	@ApiOperation("Remove jobs")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@DeleteMapping(APIPath.JobPath.$)
	public CommonResult<Void> remove(@RequestBody JobNamesParam param) {
		return CommonResult.putResult(jobOperatedService.remove(param));
	}

	@ApiOperation("Enable job")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@PutMapping(APIPath.JobPath.ENABLE)
	public CommonResult<Void> enable(@RequestBody JobNamesParam param) {
		return CommonResult.putResult(jobOperatedService.enable(param));
	}

	@ApiOperation("Disable job")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@PutMapping(APIPath.JobPath.DISABLE)
	public CommonResult<Void> disable(@RequestBody JobNamesParam param) {
		return CommonResult.putResult(jobOperatedService.disable(param));
	}

	@ApiOperation("Get job")
	@ApiImplicitParam(value = "JobNameParam", name = "param", dataType = "JobNameParam", required = true)
	@GetMapping(APIPath.JobPath.GET)
	public CommonResult<Job> get(JobNameParam param) {
		return CommonResult.putResult(jobService.get(param));
	}

	@ApiOperation("Get job content")
	@ApiImplicitParam(value = "JobNameWithVersionParam", name = "param", dataType = "JobNameWithVersionParam", required = true)
	@GetMapping(APIPath.JobPath.CONTENT)
    public CommonResult<String> getContent(JobNameWithDefinitionParam param) {
		return CommonResult.putResult(jobOperatedService.getContent(param));
	}

	@ApiOperation("List jobs by versions")
	@ApiImplicitParam(value = "JobNameParam", name = "param", dataType = "JobNameParam", required = true)
	@GetMapping(APIPath.JobPath.VERSIONS)
	public CommonResult<List<JobWithVersionResult>> listVersions(JobNameParam param) {
		return CommonResult.putResult(jobOperatedService.versions(param));
	}

	@ApiOperation("Publish job to quartz and camunda")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@PutMapping(APIPath.JobPath.PUBLISH)
	public CommonResult<Void> publish(@RequestBody JobNamesParam param) {
		return CommonResult.putResult(jobOperatedService.publish(param));
	}

	@ApiOperation("Copy job")
	@ApiImplicitParam(value = "JobCopyParam", name = "param", dataType = "JobCopyParam", required = true)
	@PutMapping(APIPath.JobPath.COPY)
	public CommonResult<Job> copy(@RequestBody JobCopyParam param) {
		return CommonResult.putResult(jobOperatedService.copy(param));
	}

	@ApiOperation("Exchange current reversion to the latest reversion")
	@ApiImplicitParam(value = "JobNameParam", name = "param", dataType = "JobNameParam", required = true)
	@PutMapping(APIPath.JobPath.EXCHANGE)
    public CommonResult<JobWithVersionResult> exchange(@RequestBody JobNameWithDefinitionParam param) {
		return CommonResult.putResult(jobOperatedService.exchange(param));
	}

	@ApiOperation("Manual start jobs")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@PostMapping(APIPath.JobPath.MANUAL_BATCH)
	public CommonResult<Void> manualBatch(@RequestBody JobNamesParam param) {
		return CommonResult.putResult(jobOperatedService.manualBatch(param));
	}

	@ApiOperation("Manual start job with param")
	@ApiImplicitParam(value = "JobNameWithJsonArgParam", name = "param", dataType = "JobNameWithJsonArgParam", required = true)
	@PostMapping(APIPath.JobPath.MANUAL)
	public CommonResult<Void> manual(@RequestBody JobNameWithJsonArgParam param) {
		return CommonResult.putResult(jobOperatedService.manual(param));
	}

	@ApiOperation("Export model")
	@ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
	@GetMapping(APIPath.JobPath.EXPORT_MODEL)
	public void exportModel(HttpServletResponse response, JobNamesParam param) {
		jobOperatedService.exportModel(response, param);
	}

	@ApiOperation("Import model")
	@ApiImplicitParam(value = "MultipartFile", name = "file", dataType = "MultipartFile", required = true)
	@PostMapping(APIPath.JobPath.IMPORT_MODEL)
	public CommonResult<Void> importModel(MultipartFile file) {
		return CommonResult.putResult(jobOperatedService.importModel(file));
	}

	@ApiOperation("List args of job")
	@ApiImplicitParam(value = "JobArgListParam", name = "param", dataType = "JobArgListParam", required = true)
	@GetMapping(APIPath.JobPath.ARG)
	public CommonResult<Map<String, Object>> listArg(JobArgListParam param) {
		return CommonResult.putResult(jobService.listArg(param));
	}

	@ApiOperation("Add arg")
	@ApiImplicitParam(value = "JobArgAllocatedParam", name = "param", dataType = "JobArgAllocatedParam", required = true)
	@PostMapping(APIPath.JobPath.ARG)
	public CommonResult<Void> addArg(@RequestBody JobArgAllocatedParam param) {
		return CommonResult.putResult(jobOperatedService.addArg(param));
	}

	@ApiOperation("Remove arg")
	@ApiImplicitParam(value = "JobArgAllocatedParam", name = "param", dataType = "JobArgAllocatedParam", required = true)
	@DeleteMapping(APIPath.JobPath.ARG)
	public CommonResult<Void> removeArg(@RequestBody JobArgAllocatedParam param) {
		return CommonResult.putResult(jobOperatedService.removeArg(param));
	}

	@ApiOperation("Get json arg")
	@ApiImplicitParam(value = "JobNameParam", name = "param", dataType = "JobNameParam", required = true)
	@GetMapping(APIPath.JobPath.JSON_ARG)
	public CommonResult<String> getJsonArg(JobNameParam param) {
		return CommonResult.putResult(jobService.getJsonArg(param));
	}

	@ApiOperation("Get project")
	@ApiImplicitParam(value = "JobNameParam", name = "param", dataType = "JobNameParam", required = true)
	@GetMapping(APIPath.JobPath.GET_PROJECT)
	public CommonResult<Project> getProject(JobNameParam param) {
		return CommonResult.putResult(jobService.getProject(param));
	}

	@ApiOperation("Save project of job")
	@ApiImplicitParam(value = "JobProjectSaveParam", name = "param", dataType = "JobProjectSaveParam", required = true)
	@PutMapping(APIPath.JobPath.SAVE_PROJECT)
	public CommonResult<Void> saveProject(@RequestBody JobProjectSaveParam param) {
		return CommonResult.putResult(jobService.saveProject(param));
	}

}