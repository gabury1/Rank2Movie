namespace MovieManager
{
    partial class MainForm
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.btnManage = new System.Windows.Forms.Button();
            this.btnUpdate = new System.Windows.Forms.Button();
            this.btnLoad = new System.Windows.Forms.Button();
            this.btnTime = new System.Windows.Forms.Button();
            this.txtLog = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("궁서", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(12, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(396, 60);
            this.label1.TabIndex = 0;
            this.label1.Text = "무-비 매네쟈";
            // 
            // btnManage
            // 
            this.btnManage.Location = new System.Drawing.Point(815, 476);
            this.btnManage.Name = "btnManage";
            this.btnManage.Size = new System.Drawing.Size(134, 61);
            this.btnManage.TabIndex = 1;
            this.btnManage.Text = "DB 관리";
            this.btnManage.UseVisualStyleBackColor = true;
            // 
            // btnUpdate
            // 
            this.btnUpdate.Location = new System.Drawing.Point(815, 409);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(134, 61);
            this.btnUpdate.TabIndex = 2;
            this.btnUpdate.Text = "업데이트 관리";
            this.btnUpdate.UseVisualStyleBackColor = true;
            // 
            // btnLoad
            // 
            this.btnLoad.Location = new System.Drawing.Point(815, 342);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(134, 61);
            this.btnLoad.TabIndex = 3;
            this.btnLoad.Text = "정보 로드";
            this.btnLoad.UseVisualStyleBackColor = true;
            // 
            // btnTime
            // 
            this.btnTime.Location = new System.Drawing.Point(675, 185);
            this.btnTime.Name = "btnTime";
            this.btnTime.Size = new System.Drawing.Size(274, 151);
            this.btnTime.TabIndex = 5;
            this.btnTime.Text = "시간";
            this.btnTime.UseVisualStyleBackColor = true;
            // 
            // txtLog
            // 
            this.txtLog.Location = new System.Drawing.Point(26, 135);
            this.txtLog.Multiline = true;
            this.txtLog.Name = "txtLog";
            this.txtLog.Size = new System.Drawing.Size(613, 402);
            this.txtLog.TabIndex = 6;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(990, 561);
            this.Controls.Add(this.txtLog);
            this.Controls.Add(this.btnTime);
            this.Controls.Add(this.btnLoad);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.btnManage);
            this.Controls.Add(this.label1);
            this.Name = "MainForm";
            this.Text = "무-비 매니저";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label1;
        private Button btnManage;
        private Button btnUpdate;
        private Button btnLoad;
        private Button btnTime;
        private TextBox txtLog;
    }
}